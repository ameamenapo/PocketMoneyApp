package com.example.demo.controller.mt;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.MtHelp;
import com.example.demo.form.mt.MtHelpEditForm;
import com.example.demo.service.mt.MtHelpEditService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MtHelpEditController {

	private final MtHelpEditService service;
	
	/*--- 編集画面表示リクエスト ---*/
	@PostMapping("/mt-show-edit")
	public String mtShowEdit(@ModelAttribute MtHelpEditForm form) {
		return "mt/mt-edit";
	}

	/*--- 更新リクエスト（編集画面より） ---*/
	@PostMapping("/mt-edit")
	public String mtEdit(
		@Validated @ModelAttribute MtHelpEditForm form,
		BindingResult result ) {

		if (result.hasErrors()) {
			return "mt/mt-edit";			// 入力がエラーの場合
		}

		return "mt/mt-confirm-edit";		// 入力が正常の場合
	}

	/*--- 更新リクエスト（編集確認画面より） ---*/
	@PostMapping("/mt-confirm-edit")
	public String mtConfirmEdit(
			@Validated @ModelAttribute MtHelpEditForm form,
			BindingResult result,
			RedirectAttributes redirectAttributes ) {

		if (result.hasErrors()) {
			return "mt/mt-edit";			// 入力がエラーの場合
		}

		MtHelp h = new MtHelp();
		h.setHelp_id(form.getHelp_id());
		h.setHelp_name(form.getHelp_name());
		h.setHelp_ammount(form.getHelp_ammount());

		service.edit(h);
			
		redirectAttributes.addFlashAttribute("msg", "(お手伝い更新)");
			
		return "redirect:/mt-complete";
	}

}
