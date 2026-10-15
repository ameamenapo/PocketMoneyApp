package com.example.demo.controller.profile;

import java.util.Base64;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.UserImage;
import com.example.demo.form.profile.UserImageForm;
import com.example.demo.service.profile.ImageServiceImple;
import com.example.demo.service.profile.MyPageImageSearchServiceImpl;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/images")
public class ImageController {

    private final ImageServiceImple service;
    private final MyPageImageSearchServiceImpl myPageImageSearchService;

	
	//画像変更ページ表示
	@GetMapping("/edit")
	private String userImageEdit(
			Model model) {
		//ユーザーIDを取得
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		 //user_image_mstからユーザーIDが一致するレコード検索
		UserImage userImage = myPageImageSearchService.findByUserId(username);

	    // Base64変換
	    if (userImage.getImage_data() != null) {
	        String base64Image = Base64.getEncoder().encodeToString(userImage.getImage_data());
	        String imageSrc = "data:" + userImage.getImage_type() + ";base64," + base64Image;
	        model.addAttribute("imageSrc", imageSrc);
	    } else {
	        model.addAttribute("imageSrc", null);
	    }

	    model.addAttribute("userImage", userImage);

	    return "profile/imageUpload";
	}
    
    // 画像アップロード
    @PostMapping("/upload")
    public String upload(
    		@ModelAttribute UserImageForm form,
    		@RequestParam("file") MultipartFile file,
    		RedirectAttributes redirectAttributes) {
        try {
            service.uploadImage(form, file);
    		redirectAttributes.addFlashAttribute("msg", "(画像変更に成功しました)");
        } catch (Exception e) {
    		redirectAttributes.addFlashAttribute("msg", "(画像変更に失敗しました)");
        }

        return "redirect:/images/edit";
    }
//    もともと使っていたメソッド。APIとかにしたいときに使える？？
//    @PostMapping("/upload")
//    public ResponseEntity<String> upload(
//    		@ModelAttribute UserImageForm form,
//    		@RequestParam("file") MultipartFile file) {
//        try {
//            String message = service.uploadImage(form, file);
//            return ResponseEntity.ok(message);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error: " + e.getMessage());
//        }
//    }

    // 画像取得(このメソッドを使うことは今のところない)
//    @GetMapping("/{id}")
//    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
//        return service.getImage_data(id)
//                .map(image -> ResponseEntity.ok()
//                        .contentType(MediaType.parseMediaType(image.getImage_type()))
//                        .body(image.getImage_data()))
//                .orElse(ResponseEntity.notFound().build());
//    }

}