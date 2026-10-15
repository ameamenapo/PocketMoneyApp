package com.example.demo.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.UserHelp;
import com.example.demo.form.user.UserRemoveForm;
import com.example.demo.service.user.UserHelpRemoveService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserHelpRemoveController {

	private final UserHelpRemoveService service;
	
	/*--- 削除リクエスト（一覧画面より） ---*/
	@PostMapping("/user-remove")
	public String userHelpRemove(
			@Validated @ModelAttribute UserRemoveForm form,
			BindingResult result) {

		if (result.hasErrors()) {
			return "user/user-remove"; // 入力がエラーの場合
		}

		return "user/user-confirm-remove"; // 入力が正常の場合
	}

	/*--- 削除リクエスト（削除確認画面より） ---*/
	@PostMapping("/user-confirm-remove")
	public String userHelpConfirmRemove(
			@Validated @ModelAttribute UserRemoveForm form,
			BindingResult result,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "user/user-remove"; // 入力がエラーの場合
		}

		UserHelp h = new UserHelp();
		h.setUser_help_id(form.getUser_help_id());
		h.setHelp_id(form.getHelp_id());
		h.setHelp_name(form.getHelp_name());
		h.setHelp_ammount(form.getHelp_ammount());
		h.setUser_id(form.getUser_id());
		h.setReported_flag(form.getReported_flag());
//		System.out.println(form);
//		System.out.println(r);

		service.remove(h);

		redirectAttributes.addFlashAttribute("msg", "(お手伝い削除)");

		return "redirect:/user-complete";
	}
}
