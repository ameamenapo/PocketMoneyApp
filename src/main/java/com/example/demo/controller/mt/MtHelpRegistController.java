package com.example.demo.controller.mt;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.MtHelp;
import com.example.demo.form.mt.MtHelpRegistForm;
import com.example.demo.service.mt.MtHelpRegistService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MtHelpRegistController {

	private final MtHelpRegistService service;
	/*--- 登録画面表示リクエスト(POSTメソッドでリクエスト来た時。ボタン推してform送信とかするとき) ---*/
	@PostMapping("/mt-show-help-regist")
	public String mtShowHelpRegist(@ModelAttribute MtHelpRegistForm form) {
		return "mt/mt-regist";
	}
	/*--- 登録画面表示リクエスト(GETメソッドでリクエスト来た時。リンクをクリックした時。) ---*/
	@GetMapping("/mt-show-help-regist")
	public String mtGetShowHelpRegist(@ModelAttribute MtHelpRegistForm form) {
		return "mt/mt-regist";
	}
	/*--- 登録リクエスト（お手伝いメニュー画面より） ---*/
	@PostMapping("/mt-show-regist")
	public String mtGetShowRegist(@Validated @ModelAttribute MtHelpRegistForm form,
			BindingResult result) {
		return "mt/mt-regist";
	}
	/*--- 登録リクエスト（登録画面より） ---*/
	@PostMapping("/mt-regist")
	public String mtRegist(
		@Validated @ModelAttribute MtHelpRegistForm form,
		BindingResult result ) {

		if (result.hasErrors()) {
			return "mt/mt-regist";			// 入力がエラーの場合
		}

		return "mt/mt-confirm-regist";		// 入力が正常の場合
	}

	/*--- 登録リクエスト（登録確認画面より） ---*/
	@PostMapping("/mt-confirm-help-regist")
	public String mtConfirmRegist(
			@Validated @ModelAttribute MtHelpRegistForm form,
			BindingResult result,
			RedirectAttributes redirectAttributes ) {

		if (result.hasErrors()) {
			return "mt/mt-regist";			// 入力がエラーの場合
		}

		MtHelp h = new MtHelp();
		h.setHelp_name(form.getHelpName());
		h.setHelp_ammount(form.getHelpAmomunt());

		service.regist(h);
			
		redirectAttributes.addFlashAttribute("msg", "(お手伝い登録)");
			
		return "redirect:/mt-complete";
	}

}
