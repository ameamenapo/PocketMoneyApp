package com.example.demo.controller.profile;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Profile;
import com.example.demo.form.profile.ProfileEditForm;
import com.example.demo.service.profile.MyPageProfileEditServiceImpl;
import com.example.demo.service.profile.MyPageProfileSearchServiceImpl;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private final MyPageProfileSearchServiceImpl myPageProfileSearchService;
    private final MyPageProfileEditServiceImpl myPageProfileEditService;

	
	//プロフィール変更ページ表示
	@PostMapping("/edit")
	private String userProfileEdit(
			@ModelAttribute ProfileEditForm form, 
			Model model) {
		//ユーザーIDを取得
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		 //profile_mstからユーザーIDが一致するレコード検索
		Profile userProfile = myPageProfileSearchService.findByUserId(username);

	    model.addAttribute("userProfile", userProfile);

	    return "profile/profileEdit";
	}
	
	//プロフィール変更ページ表示（リダイレクト用）
	@GetMapping("/edit")
	private String getUserProfileEdit(
			@ModelAttribute ProfileEditForm form, 
			Model model) {
		//ユーザーIDを取得
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		 //profile_mstからユーザーIDが一致するレコード検索
		Profile userProfile = myPageProfileSearchService.findByUserId(username);

//	    model.addAttribute("profileEditForm", profileEditForm);
	    model.addAttribute("userProfile", userProfile);

	    return "profile/profileEdit";
	}
    
    // プロフィール更新リクエスト（編集画面より）
    @PostMapping("/update")
    public String upload(
    		@Validated @ModelAttribute ProfileEditForm form,
    		BindingResult result ) {
    	
		if (result.hasErrors()) {
			return "profile/profileEdit";			// 入力がエラーの場合
		}

		return "profile/profileEdit-confirm";		// 入力が正常の場合
    }
    
	/*--- プロフィール更新リクエスト（編集確認画面より） ---*/
	@PostMapping("/update-execute")
	public String uploadConfirm(
			@Validated @ModelAttribute ProfileEditForm form,
			BindingResult result,
			RedirectAttributes redirectAttributes ) {

		if (result.hasErrors()) {
			return "profile/profileEdit";			// 入力がエラーの場合
		}

		Profile p = new Profile();
		p.setProfile_id(form.getProfile_id());
		p.setUser_id(form.getUser_id());
		p.setProfile_name(form.getProfile_name());
		p.setBirth_year(form.getBirth_year());
		p.setBirth_month(form.getBirth_month());
		p.setBirth_day(form.getBirth_day());
		p.setSaving_goal(form.getSaving_goal());
		p.setWhat_i_want(form.getWhat_i_want());
		p.setComments(form.getComments());

		myPageProfileEditService.edit(p);
			
		redirectAttributes.addFlashAttribute("msg", "(プロフィール更新が完了しました。)");
			
		return "redirect:/profile/edit";
	}


}