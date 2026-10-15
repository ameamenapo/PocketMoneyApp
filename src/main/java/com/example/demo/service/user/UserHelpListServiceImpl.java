package com.example.demo.service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.MtHelp;
import com.example.demo.repository.user.UserHelpListRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserHelpListServiceImpl implements UserHelpListService {

	private final UserHelpListRepository repository;
	
	//検索
	@Override
	public List<MtHelp> findByNameWildcard(String helpName) {	

		List<MtHelp> list
			= repository.selectByNameWildcard(helpName);
		
		return list;
	}
	

}
