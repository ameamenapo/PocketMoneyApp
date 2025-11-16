package com.example.demo.controller.mt;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.mt.MtReportedHelpListForm;
import com.example.demo.form.user.UserRegisterForm;

@Controller
public class MtCommonController {

	/*--- 管理画面トップ画面表示 ---*/
	@GetMapping("/mt-top")
	private String mtGetShowTop() {
		return "mt/mt-top";
	}
	/*--- 管理画面トップ画面表示 ---*/
	@PostMapping("/mt-top")
	private String mtShowTop() {
		return "mt/mt-top";
	}

	/*--- お手伝いメニュー画面表示 ---*/
	@PostMapping("/mt-show-help-menu")
	private String mtShowHelpMenu() {
		return "mt/mt-help-menu";
	}
	@GetMapping("/mt-show-help-menu")
	private String mtGetShowHelpMenu() {
		return "mt/mt-help-menu";
	}
	
	/*--- トップ画面から報告されたお手伝い画面表示リクエスト(reported_help_listに登録されている、未承認のお手伝い一覧) ---*/
	@PostMapping("/mt-show-reported-help-list")
	public String mtShowReportedHelpList(@ModelAttribute MtReportedHelpListForm form) {
		return "mt/mt-reported-help-list";
	}
	/*--- 報告されたお手伝い画面表示リクエスト(reported_help_listに登録されている、未承認のお手伝い一覧・Getリクエスト) ---*/
	@GetMapping("/mt-show-reported-help-list")
	public String mtGetShowReportedHelpList(@ModelAttribute MtReportedHelpListForm form) {
		return "mt/mt-reported-help-list";
	}
	
	/*--- ユーザ登録 ---*/
	@PostMapping("/mt-show-user-regist")
	public String mtShowUserRegist(@ModelAttribute UserRegisterForm form) {
		return "mt/mt-user-regist";
	}
	
	/*--- 完了後のリダイレクト先 ---*/
	@GetMapping("/mt-complete")
	private String mtComplete() {
		return "mt/mt-complete";
	}

}
