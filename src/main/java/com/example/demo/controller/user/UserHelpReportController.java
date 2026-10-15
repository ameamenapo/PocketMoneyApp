package com.example.demo.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.UserHelp;
import com.example.demo.entity.UserHelpReport;
import com.example.demo.form.user.UserMyListForm;
import com.example.demo.service.user.UserHelpReportService;
//import com.example.demo.service.user.UserHelpReportService;
import com.example.demo.service.user.UserMyHelpListService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserHelpReportController {
	
	private final UserMyHelpListService service;
	private final UserHelpReportService reportedService;
	
	/*--- 報告リクエスト（MyList画面より） ---*/
	@PostMapping("/user-report")
	public String userReport(
		@Validated @ModelAttribute UserMyListForm form, 
		BindingResult result ) {

		if (result.hasErrors()) {
			return "user/user-help-list";			// 入力がエラーの場合
		}

		return "user/user-confirm-report";		// 入力が正常の場合
	}

	/*--- 報告リクエスト（報告確認画面より） ---*/	
	@PostMapping("/user-confirm-help-report")
	public String userConfirmReport(
			@Validated @ModelAttribute UserMyListForm form,
			BindingResult result,
			RedirectAttributes redirectAttributes ) {

		if (result.hasErrors()) {
			return "user/user-help-list";			// 入力がエラーの場合
		}

		//user_help_listのreported_flagを1にして報告ずみお手伝いとして更新。(Myリストから削除)
		UserHelp h = new UserHelp();
		h.setUser_help_id(form.getUser_help_id());
		h.setHelp_id(form.getHelp_id());
		h.setHelp_name(form.getHelp_name());
		h.setHelp_ammount(form.getHelp_ammount());
		h.setUser_id(form.getUser_id());
		h.setReported_flag(1);
		service.regist(h);
		
		//reported_help_listにMyリストから削除したお手伝いを登録。
		UserHelpReport rh = new UserHelpReport();
		rh.setHelp_id(form.getHelp_id());
		rh.setHelp_name(form.getHelp_name());
		rh.setHelp_ammount(form.getHelp_ammount());
		rh.setUser_id(form.getUser_id());
		rh.setApproved_flag(0);
		reportedService.reportedHelpRegist(rh);

			
		redirectAttributes.addFlashAttribute("msg", "(お手伝い報告)");
			
		return "redirect:/user-complete";
	}


}
