package com.example.demo.controller.user;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.BankAccount;
import com.example.demo.form.bankaccount.BankAccountForm;
import com.example.demo.form.user.UserRegisterForm;
import com.example.demo.service.bankaccount.DepositSearchService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserCommonController {

	private final DepositSearchService service;

	/*--- ユーザー画面トップ画面表示 ---*/
	@GetMapping("/user-top")
	private String userGetShowTop(@ModelAttribute BankAccountForm form, Model model) {

		//ログイン画面からユーザID取得して口座マスタに検索する。
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		List<BankAccount> bankList = service.findByDeposit(username);
		if (bankList.size() > 0) {
			model.addAttribute("bankList", bankList);
		}
		
		return "user/user-top";
	}
	@PostMapping("/user-top")
	private String userShowTop(@ModelAttribute UserRegisterForm form, Model model) {

		//ログイン画面からユーザID取得して口座マスタに検索する。
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		List<BankAccount> bankList = service.findByDeposit(username);
		if (bankList.size() > 0) {
			model.addAttribute("bankList", bankList);
		}
		
		return "user/user-top";
	}

	/*--- お手伝いメニュー画面表示 ---*/
	@PostMapping("/user-show-help-menu")
	private String userShowHelpMenu() {
		return "user/user-help-menu";
	}
	@GetMapping("/user-show-help-menu")
	private String userGetShowHelpMenu() {
		return "user/user-help-menu";
	}
	
	/*--- 完了後のリダイレクト先 ---*/
	@GetMapping("/user-complete")
	private String userComplete() {
		return "user/user-complete";
	}

}
