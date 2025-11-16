package com.example.demo.controller.user;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.UserHelp;
import com.example.demo.form.user.UserRegistForm;
import com.example.demo.service.user.UserHelpRegistService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserHelpRegistController {

	private final UserHelpRegistService service;

	/*--- 登録リクエスト（登録画面より） ---*/
	@PostMapping("/user-regist")
	public String userHelpRegist(
		@Validated @ModelAttribute UserRegistForm form,
		BindingResult result ) {

		if (result.hasErrors()) {
			return "user/user-regist";			// 入力がエラーの場合
		}

		return "user/user-confirm-regist";		// 入力が正常の場合
	}

	/*--- 登録リクエスト（登録確認画面より） ---*/
	@PostMapping("/user-confirm-help-regist")
	public String userHelpConfirmRegist(
			@Validated @ModelAttribute UserRegistForm form,
			BindingResult result,
			RedirectAttributes redirectAttributes ) {

		if (result.hasErrors()) {
			return "user/user-regist";			// 入力がエラーの場合
		}

		UserHelp h = new UserHelp();
		h.setHelp_name(form.getHelp_name());
		h.setHelp_ammount(form.getHelp_ammount());
		//今ログインしているユーザidを取得
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		h.setUser_id(username);
		//h.setUser_id("1");
		h.setReported_flag(0);

		service.regist(h);
			
		redirectAttributes.addFlashAttribute("msg", "(お手伝い登録)");
			
		return "redirect:/user-complete";
	}
	


}
