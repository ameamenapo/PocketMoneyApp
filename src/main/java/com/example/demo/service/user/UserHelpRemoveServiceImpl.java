package com.example.demo.service.user;

import org.springframework.stereotype.Service;

import com.example.demo.entity.UserHelp;
import com.example.demo.repository.user.UserHelpListRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserHelpRemoveServiceImpl implements UserHelpRemoveService {

	private final UserHelpListRepository repository;

	@Override
	public void remove(UserHelp h) {

		repository.delete(h);

	}

}
