package com.example.demo.service.user;

import org.springframework.stereotype.Service;

import com.example.demo.entity.UserHelp;
import com.example.demo.repository.user.UserHelpRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserHelpRegistServiceImpl implements UserHelpRegistService {

	private final UserHelpRepository repository;
	
	@Override
	public void regist(UserHelp h) {

		repository.add(h);
		
	}
	

}
