package com.example.demo.controller.mt;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.BankAccount;
import com.example.demo.entity.MtHelpApprove;
import com.example.demo.entity.MtHelpNotApprove;
import com.example.demo.entity.MtHelpReport;
import com.example.demo.form.mt.MtReportedHelpListForm;
import com.example.demo.service.bankaccount.DepositAddService;
import com.example.demo.service.bankaccount.DepositSearchService;
import com.example.demo.service.mt.MtApprovedHelpListService;
import com.example.demo.service.mt.MtReportedHelpListService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MtReportedHelpListController {
	
	private final MtReportedHelpListService service;
	private final MtApprovedHelpListService approvedService;
	private final DepositAddService depositAddService;
	private final DepositSearchService depositservice;
	

	@PostMapping("/mt-reported-help-search")
	private String mtSearchReportedHelp(
		@ModelAttribute MtReportedHelpListForm form,
		Model model	) {

		List<MtHelpReport> list 
			= service.findByNameWildcard(form.getHelp_name());
		
		if (list.size() > 0) {
			model.addAttribute("helpList", list);
		}
		
		return "mt/mt-reported-help-list";
	}
	
	/*--- 承認リクエスト（報告されたお手伝い画面より） ---*/
	@PostMapping("/mt-approve")
	public String mtApprove(
		@Validated @ModelAttribute MtReportedHelpListForm form,
		BindingResult result ) {

		if (result.hasErrors()) {
			return "mt/mt-reported-help-list";			// 入力がエラーの場合
		}

		return "mt/mt-confirm-reported-help-list";		// 入力が正常の場合
	}

	/*--- 承認リクエスト（承認確認画面より） ---*/
	@PostMapping("/mt-confirm-reported-help")
	public String mtConfirmApprove(
			@Validated @ModelAttribute MtReportedHelpListForm form,
			BindingResult result,
			RedirectAttributes redirectAttributes ) {

		if (result.hasErrors()) {
			return "mt/mt-reported-help-list";			// 入力がエラーの場合
		}

		//reported_help_listのapproved_flagを1にしてお手伝いを承認済みとする。
		MtHelpReport h = new MtHelpReport();
		h.setReported_help_id(form.getReported_help_id());
		h.setHelp_id(form.getHelp_id());
		h.setHelp_name(form.getHelp_name());
		h.setHelp_ammount(form.getHelp_ammount());
		h.setUser_id(form.getUser_id());
		h.setApproved_flag(1);	
		service.approve(h);
		
		//bank_account_mstのfinancial_assetに承認したお手伝いのお小遣いを足す。
		List<BankAccount> bankList = depositservice.findByDeposit(form.getUser_id());
		for (BankAccount one : bankList) {
			BankAccount ba = new BankAccount();
			//user_help_listのreported_flagが0ならhelpListに詰めてMyリストで表示
			ba.setFinancial_asset((int)one.getFinancial_asset());
			ba.setUser_id((String)one.getUser_id());
			
			int money = ba.getFinancial_asset();
		    int total = form.getHelp_ammount() + money;
			
		    ba.setFinancial_asset(total);

		    depositAddService.depositAdd(ba);
		}
		
		//approved_help_listにreported_help_listから削除したお手伝いを登録。
		MtHelpApprove ah = new MtHelpApprove();
		ah.setReported_help_id(form.getReported_help_id());
		ah.setHelp_id(form.getHelp_id());
		ah.setHelp_name(form.getHelp_name());
		ah.setHelp_ammount(form.getHelp_ammount());
		ah.setUser_id(form.getUser_id());	
		approvedService.approvedHelpRegist(ah);
			
		redirectAttributes.addFlashAttribute("msg", "(お手伝い承認)");
			
		return "redirect:/mt-complete";
	}
	
	/*--- 差戻しリクエスト（報告されたお手伝い画面より） ---*/
	@PostMapping("/mt-not-approve")
	public String mtNotApprove(
		@Validated @ModelAttribute MtReportedHelpListForm form,
		BindingResult result ) {

		if (result.hasErrors()) {
			return "mt/mt-reported-help-list";			// 入力がエラーの場合
		}

		return "mt/mt-confirm-not-approved-help-list";		// 入力が正常の場合
	}
	
	/*--- 差し戻しリクエスト（差戻確認画面より） ---*/
	@PostMapping("/mt-confirm-not-approved-help")
	public String mtConfirmNotApprovedRegist(
			@Validated @ModelAttribute MtReportedHelpListForm form,
			BindingResult result,
			RedirectAttributes redirectAttributes ) {

		if (result.hasErrors()) {
			return "mt/mt-reported-help-list";			// 入力がエラーの場合
		}

		//not_approved_help_listのreported_flagを0にして差戻しお手伝いとして登録する。
		MtHelpNotApprove h = new MtHelpNotApprove();
		h.setReported_help_id(form.getReported_help_id());
		h.setHelp_id(form.getHelp_id());
		h.setHelp_name(form.getHelp_name());
		h.setHelp_ammount(form.getHelp_ammount());
		h.setUser_id(form.getUser_id());
		h.setReported_flag(0);	
		approvedService.notApprovedHelpRegist(h);
		
		//reported_help_listのapproved_flagカラムを2(差戻し)にして、管理がわの報告されたお手伝い画面に表示されないようにする
		MtHelpReport noth = new MtHelpReport();
		noth.setReported_help_id(form.getReported_help_id());
		noth.setHelp_id(form.getHelp_id());
		noth.setHelp_name(form.getHelp_name());
		noth.setHelp_ammount(form.getHelp_ammount());
		noth.setUser_id(form.getUser_id());
		noth.setApproved_flag(2);	
		service.notApprove(noth);
		
		redirectAttributes.addFlashAttribute("msg", "(お手伝い差戻し)");
			
		return "redirect:/mt-complete";
	}

}
