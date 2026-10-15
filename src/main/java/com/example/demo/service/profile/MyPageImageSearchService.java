package com.example.demo.service.profile;

import org.springframework.stereotype.Service;

import com.example.demo.entity.UserImage;

@Service
public interface MyPageImageSearchService {

//	画像検索
	UserImage findByUserId(String userId);
}
