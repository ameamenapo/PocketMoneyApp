package com.example.demo.service.mt;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.MtHelp;
import com.example.demo.repository.mt.MtHelpListRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MtHelpListServiceImpl implements MtHelpListService {

	private final MtHelpListRepository repository;
	
	//検索
	@Override
	public List<MtHelp> findByNameWildcard(String helpName) {	

		List<MtHelp> list
			= repository.selectByNameWildcard(helpName);
		
		return list;
	}
	

}
