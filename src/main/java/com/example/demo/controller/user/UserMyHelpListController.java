package com.example.demo.controller.user;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.UserHelp;
import com.example.demo.form.user.UserMyListForm;
import com.example.demo.service.user.UserMyHelpListService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserMyHelpListController {
	
	private final UserMyHelpListService service;
	
	/*--- Myリスト検索画面表示リクエスト(user_help_listに登録されている全お手伝い一覧・Postリクエスト) ---*/
	@PostMapping("/user-show-myhelp-list")
	public String userShowMyHelpRegist(@ModelAttribute UserMyListForm form) {
		return "user/user-help-list";
	}
	/*--- Myリスト検索画面表示リクエスト(user_help_listに登録されている全お手伝い一覧・Getリクエスト) ---*/
	@GetMapping("/user-show-myhelp-list")
	public String userGetShowMyHelpRegist(@ModelAttribute UserMyListForm form) {
		return "user/user-help-list";
	}
	
	@PostMapping("/user-myhelp-search")
	private String userSearchHelp(
		@ModelAttribute UserMyListForm form,
		Model model	) {

		List<UserHelp> list 
			= service.findByNameWildcard(form.getHelp_name());
		
		if (list.size() > 0) {
			model.addAttribute("helpList", list);
		}
		
		return "user/user-help-list";
	}

}
