package com.example.demo.service.profile;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Profile;
import com.example.demo.repository.profile.MyPageProfileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyPageProfileEditServiceImpl implements MyPageProfileEditService{

	private final MyPageProfileRepository repository;

	@Override
	public void edit(Profile p) {

		repository.update(p);

	}

}
