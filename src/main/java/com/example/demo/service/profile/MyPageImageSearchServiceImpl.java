package com.example.demo.service.profile;

import org.springframework.stereotype.Service;

import com.example.demo.entity.UserImage;
import com.example.demo.repository.profile.MyPageImageSearchRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyPageImageSearchServiceImpl implements MyPageImageSearchService {

	private final MyPageImageSearchRepository repository;
	
	//ユーザー画像検索
	@Override
	public UserImage findByUserId(String userId) {	

		UserImage image = repository.selectByUserId(userId);
		
		return image;
	}
	

}
