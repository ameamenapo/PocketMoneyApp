package com.example.demo.service.profile;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Profile;
import com.example.demo.repository.profile.MyPageProfileSearchRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyPageProfileSearchServiceImpl implements MyPageProfileSearchService {

	private final MyPageProfileSearchRepository repository;

	//ユーザープロフィール検索
	@Override
	public Profile findByUserId(String userId) {

		Profile list = repository.selectByUserId(userId);

		return list;

	}

}
