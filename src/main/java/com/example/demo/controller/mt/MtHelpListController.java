package com.example.demo.controller.mt;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.MtHelp;
import com.example.demo.form.mt.MtHelpListForm;
import com.example.demo.service.mt.MtHelpListService;

import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class MtHelpListController {

	private final MtHelpListService service;
	/*--- 検索画面表示リクエスト ---*/
	@PostMapping("/show-help-list")
	private String showHelpList(@ModelAttribute MtHelpListForm form) {
		//最初の検索画面への画面遷移でも、今は「該当する手伝いなし」と表示されてしまう。これを防ぐには、
		//リポジトリとか全部「検索画面への初回アクセス画面よう」を作らないといけない。時間あれば作る。
		return "mt/mt-help-list";
	}
	@GetMapping("/show-help-list")
	private String getshowHelpList(@ModelAttribute MtHelpListForm form) {
		//最初の検索画面への画面遷移でも、今は「該当する手伝いなし」と表示されてしまう。これを防ぐには、
		//リポジトリとか全部「検索画面への初回アクセス画面よう」を作らないといけない。時間あれば作る。
		return "mt/mt-help-list";
	}

	/*--- 検索リクエスト ---*/
	//部分検索
	@PostMapping("/mt-search")
	private String searchHelp(
		@ModelAttribute MtHelpListForm form,
		Model model	) {

		List<MtHelp> list 
			= service.findByNameWildcard(form.getHelp_name());
		
		if (list.size() > 0) {
			model.addAttribute("helpList", list);
		}
		
		return "mt/mt-help-list";
	}
	

}
