package com.example.demo.service.profile;

import org.springframework.stereotype.Service;

import com.example.demo.repository.profile.MyPageImageRepository;
import com.example.demo.repository.profile.MyPageProfileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyPageRegistServiceImpl implements MyPageRegistService {

	private final MyPageImageRepository repository;
	private final MyPageProfileRepository prepository;
	
	@Override
	public void imageRegist(String username) {

		repository.add(username);
		
	}
	
	@Override
	public void profileRegist(String username) {

		prepository.add(username);
		
	}
	

}
