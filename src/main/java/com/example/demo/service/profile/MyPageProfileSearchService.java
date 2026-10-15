package com.example.demo.service.profile;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Profile;

@Service
public interface MyPageProfileSearchService {

//	プロフィール検索
	Profile findByUserId(String userId);
}
