package com.example.demo.controller.user;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.MtHelp;
import com.example.demo.form.user.AllHelpListForm;
import com.example.demo.service.user.UserHelpListService;

import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class UserHelpListController {

	private final UserHelpListService service;
	/*--- 検索画面表示リクエスト ---*/
	@PostMapping("/user-show-help-list")
	private String showHelpList(@ModelAttribute AllHelpListForm form) {
		//最初の検索画面への画面遷移でも、今は「該当する手伝いなし」と表示されてしまう。これを防ぐには、
		//リポジトリとか全部「検索画面への初回アクセス画面よう」を作らないといけない。時間あれば作る。
		return "user/all-help-list";
	}

	@GetMapping("/user-show-help-list")
	private String getshowHelpList(@ModelAttribute AllHelpListForm form) {
		//最初の検索画面への画面遷移でも、今は「該当する手伝いなし」と表示されてしまう。これを防ぐには、
		//リポジトリとか全部「検索画面への初回アクセス画面よう」を作らないといけない。時間あれば作る。
		return "user/all-help-list";
	}

	
	//部分検索
	@PostMapping("/all-help-search")
	private String searchHelp(
		@ModelAttribute AllHelpListForm form,
		Model model	) {
		//help_mstを取得したいので、エンティティはMtHelpを使う
		List<MtHelp> list 
			= service.findByNameWildcard(form.getHelp_name());
		
		if (list.size() > 0) {
			model.addAttribute("helpList", list);
		}
		
		return "user/all-help-list";
	}


}
