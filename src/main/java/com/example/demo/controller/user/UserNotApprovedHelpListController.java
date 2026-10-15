package com.example.demo.controller.user;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.MtHelpNotApprove;
import com.example.demo.entity.UserHelpReport;
import com.example.demo.form.user.UserNotApprovedHelpListForm;
import com.example.demo.service.user.UserHelpReportService;
import com.example.demo.service.user.UserNotApprovedHelpListService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserNotApprovedHelpListController {

	
	private final UserNotApprovedHelpListService service;
	private final UserHelpReportService reportedService;
	
	/*--- 検索画面表示リクエスト(not_approved_help_listに登録されている全お手伝い一覧・Postリクエスト) ---*/
	@PostMapping("/user-show-not-approved-help")
	public String userShowNotApprovedHelp(@ModelAttribute UserNotApprovedHelpListForm form) {
		return "user/user-not-approved-help-list";
	}
	/*--- 検索画面表示リクエスト(not_approved_help_listに登録されている全お手伝い一覧・Getリクエスト) ---*/
	@GetMapping("/user-show-not-approved-help")
	public String userGetShowNotApprovedHelp(@ModelAttribute UserNotApprovedHelpListForm form) {
		return "user/user-not-approved-help-list";
	}
	
	@PostMapping("/user-not-approved-search")
	private String userSearchNotApprovedHelp(
		@ModelAttribute UserNotApprovedHelpListForm form,
		Model model) {

		List<MtHelpNotApprove> list 
			= service.findByNameWildcard(form.getHelp_name());
		
		if (list.size() > 0) {
			model.addAttribute("helpList", list);
		}
		
		return "user/user-not-approved-help-list";
	}
	
	/*--- 報告リクエスト（差戻し画面より） ---*/
	@PostMapping("/user-again-report")
	public String userAgainReport(
		@Validated @ModelAttribute UserNotApprovedHelpListForm form, 
		BindingResult result ) {

		if (result.hasErrors()) {
			return "user/user-not-approved-help-list";			// 入力がエラーの場合
		}

		return "user/user-confirm-again-report";		// 入力が正常の場合
	}

	/*--- 報告リクエスト（報告確認画面より） ---*/	
	@PostMapping("/user-confirm-again-help-report")
	public String userConfirmAgainReport(
			@Validated @ModelAttribute UserNotApprovedHelpListForm form,
			BindingResult result,
			RedirectAttributes redirectAttributes ) {

		if (result.hasErrors()) {
			return "user/user-not-approved-help-list";			// 入力がエラーの場合
		}

		//not_approved_help_listのreported_flagを1にして報告ずみお手伝いとして更新。(差戻し一覧から削除)
		MtHelpNotApprove h = new MtHelpNotApprove();
		h.setNot_approved_help_id(form.getNot_approved_help_id());
		h.setReported_help_id(form.getReported_help_id());
		h.setHelp_id(form.getHelp_id());
		h.setHelp_name(form.getHelp_name());
		h.setHelp_ammount(form.getHelp_ammount());
		h.setUser_id(form.getUser_id());
		h.setReported_flag(1);
//		System.out.println(form);
//		System.out.println(h);
		service.notapproveflagRegist(h);
		
		//reported_help_listに差戻し一覧から削除したお手伝いを登録。(管理側の「報告されたお手伝い」画面に再表示する)
		UserHelpReport rh = new UserHelpReport();
		rh.setHelp_id(form.getHelp_id());
		rh.setHelp_name(form.getHelp_name());
		rh.setHelp_ammount(form.getHelp_ammount());
		rh.setUser_id(form.getUser_id());
		rh.setApproved_flag(0);
//		System.out.println(form);
//		System.out.println(rh);		
		reportedService.reportedHelpRegist(rh);
		
		redirectAttributes.addFlashAttribute("msg", "(お手伝い報告)");
			
		return "redirect:/user-complete";
	}
	
	/*--- 取り下げリクエスト（差戻し画面より） ---*/
	@PostMapping("/user-not-approved-remove")
	public String userRemoveReport(
		@Validated @ModelAttribute UserNotApprovedHelpListForm form, 
		BindingResult result ) {

		if (result.hasErrors()) {
			return "user/user-not-approved-help-list";			// 入力がエラーの場合
		}

		return "user/user-confirm-approved-remove-report";		// 入力が正常の場合
	}
	
	/*--- 取り下げリクエスト（取り下げ確認画面より） ---*/	
	@PostMapping("/user-confirm-remove-help-report")
	public String userConfirmRemoveReport(
			@Validated @ModelAttribute UserNotApprovedHelpListForm form,
			BindingResult result,
			RedirectAttributes redirectAttributes ) {

		if (result.hasErrors()) {
			return "user/user-not-approved-help-list";			// 入力がエラーの場合
		}

		//not_approved_help_listのreported_flagを2にして取り下げお手伝いとして更新。(差戻し一覧から削除)
		MtHelpNotApprove h = new MtHelpNotApprove();
		h.setNot_approved_help_id(form.getNot_approved_help_id());
		h.setReported_help_id(form.getReported_help_id());
		h.setHelp_id(form.getHelp_id());
		h.setHelp_name(form.getHelp_name());
		h.setHelp_ammount(form.getHelp_ammount());
		h.setUser_id(form.getUser_id());
		h.setReported_flag(2);
//		System.out.println(form);
//		System.out.println(h);
		service.notapproveflagRegist(h);
		
		redirectAttributes.addFlashAttribute("msg", "(お手伝い報告取り下げ)");
			
		return "redirect:/user-complete";
	}



}
