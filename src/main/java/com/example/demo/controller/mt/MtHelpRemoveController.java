package com.example.demo.controller.mt;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.MtHelp;
import com.example.demo.form.mt.MtHelpRemoveForm;
import com.example.demo.service.mt.MtHelpRemoveService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MtHelpRemoveController {

	private final MtHelpRemoveService service;
	
	/*--- 削除リクエスト（一覧画面より） ---*/
	@PostMapping("/mt-remove")
	public String mtRemove(
			@Validated @ModelAttribute MtHelpRemoveForm form,
			BindingResult result) {

		if (result.hasErrors()) {
			return "mt/mt-help-list"; // 入力がエラーの場合
		}

		return "mt/mt-confirm-remove"; // 入力が正常の場合
	}

	/*--- 削除リクエスト（削除確認画面より） ---*/
	@PostMapping("/mt-confirm-remove")
	public String confirmRemove(
			@Validated @ModelAttribute MtHelpRemoveForm form,
			BindingResult result,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "mt/mt-confirm-remove"; // 入力がエラーの場合
		}

		MtHelp h = new MtHelp();
		h.setHelp_id(form.getHelp_id());
		h.setHelp_name(form.getHelp_name());
		h.setHelp_ammount(form.getHelp_ammount());

		service.remove(h);

		redirectAttributes.addFlashAttribute("msg", "(お手伝い削除)");

		return "redirect:/mt-complete";
	}
}
