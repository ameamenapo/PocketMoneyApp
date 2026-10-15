package com.example.demo.controller.bankaccount;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.BankAccount;
import com.example.demo.form.bankaccount.BankAccountForm;
import com.example.demo.service.mt.MtAccountRegistService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MtAccountRegistController {

	private final MtAccountRegistService service;
	/*--- 口座登録画面表示リクエスト(管理画面トップより) ---*/
	@PostMapping("/mt-show-account-regist")
	public String mtShowAccountRegist(@ModelAttribute BankAccountForm form) {
		return "mt/mt-account-regist";
	}


	/*--- 登録リクエスト（登録画面より） ---*/
	@PostMapping("/mt-account-regist")
	public String mtAccountRegist(
		@Validated @ModelAttribute BankAccountForm form,
		BindingResult result ) {

		if (result.hasErrors()) {
			return "mt/mt-account-regist";			// 入力がエラーの場合
		}

		return "mt/mt-confirm-account-regist";		// 入力が正常の場合
	}

	/*--- 登録リクエスト（登録確認画面より） ---*/
	@PostMapping("/mt-confirm-account-regist")
	public String mtConfirmAccountRegist(
			@Validated @ModelAttribute BankAccountForm form,
			BindingResult result,
			RedirectAttributes redirectAttributes ) {

		if (result.hasErrors()) {
			return "mt/mt-account-regist";			// 入力がエラーの場合
		}

		BankAccount ba = new BankAccount();
		ba.setUser_id(form.getUserName());
		ba.setFinancial_asset(form.getFinancial_asset());

		service.regist(ba);
			
		redirectAttributes.addFlashAttribute("msg", "(口座登録)");
			
		return "redirect:/mt-complete";
	}

}
