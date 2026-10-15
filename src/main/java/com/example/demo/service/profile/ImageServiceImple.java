package com.example.demo.service.profile;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.UserImage;
import com.example.demo.form.profile.UserImageForm;
import com.example.demo.repository.profile.ImageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
//public class ImageServiceImple implements ImageService {
public class ImageServiceImple {
	
	private final ImageRepository repository;
 
    // 画像アップロード処理
//    public String uploadImage(UserImageForm form, MultipartFile file) throws IOException {
    public void uploadImage(UserImageForm form, MultipartFile file) throws IOException {
    	UserImage image = new UserImage();
    	image.setImage_id(form.getImage_id());
    	image.setUser_id(form.getUser_id());
        image.setImage_name(file.getOriginalFilename());
        image.setImage_type(file.getContentType());
        image.setImage_data(file.getBytes());
        repository.save(image);
//        return "Uploaded successfully: ID = " + image.getImage_id();
    }

    // 画像取得処理
    public Optional<UserImage> getImage_data(Long id) {
        return repository.findById(id);
    


}
}
