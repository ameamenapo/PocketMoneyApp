package com.example.demo.service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.UserHelp;
import com.example.demo.repository.user.UserMyHelpListRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserMyHelpListServiceImpl implements UserMyHelpListService {
	
	private final UserMyHelpListRepository repository;
	
	//検索
	@Override
	public List<UserHelp> findByNameWildcard(String helpName) {

		List<UserHelp> list
			= repository.selectByNameWildcard(helpName);
		
		return list;
	}
	
	@Override
	public void regist(UserHelp h) {

		repository.update(h);
		
	}

}
