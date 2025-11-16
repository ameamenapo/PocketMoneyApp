package com.example.demo.controller.profile;

import java.util.Base64;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Profile;
import com.example.demo.entity.UserImage;
import com.example.demo.service.profile.MyPageImageSearchServiceImpl;
import com.example.demo.service.profile.MyPageProfileSearchServiceImpl;
import com.example.demo.service.profile.MyPageRegistServiceImpl;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPageController {


    private final MyPageRegistServiceImpl myPageService;
    private final MyPageImageSearchServiceImpl myPageImageSearchService;
    private final MyPageProfileSearchServiceImpl myPageProfileSearchService;

    //My page表示
	@GetMapping("/user-show-mypage")
	private String userShowMypage(
//			@ModelAttribute UserImageForm form, 
//			@ModelAttribute ProfileRegistForm pform, 
			Model model) {
		//ユーザーIDを取得
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		 //user_image_mstに上記のユーザーIDがなければレコード登録
		UserImage userImage = myPageImageSearchService.findByUserId(username);
	    if (userImage == null) {
	        myPageService.imageRegist(username);
	        userImage = myPageImageSearchService.findByUserId(username);
	    }

	    // Base64変換
	    if (userImage.getImage_data() != null) {
	        String base64Image = Base64.getEncoder().encodeToString(userImage.getImage_data());
	        String imageSrc = "data:" + userImage.getImage_type() + ";base64," + base64Image;
	        model.addAttribute("imageSrc", imageSrc);
	    } else {
	        model.addAttribute("imageSrc", null);
	    }
	    
		 //profile_mstに上記のユーザーIDがなければレコード登録
	    Profile userProfile = myPageProfileSearchService.findByUserId(username);
	    if (userProfile == null) {
	        myPageService. profileRegist(username);
	        userProfile = myPageProfileSearchService.findByUserId(username);
	    }

	    model.addAttribute("userImage", userImage);
	    model.addAttribute("userProfile", userProfile);

	    return "profile/myPage";
	}



}