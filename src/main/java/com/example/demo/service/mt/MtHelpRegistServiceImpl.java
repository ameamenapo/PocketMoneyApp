package com.example.demo.service.mt;

import org.springframework.stereotype.Service;

import com.example.demo.entity.MtHelp;
import com.example.demo.repository.mt.MtHelpRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MtHelpRegistServiceImpl implements MtHelpRegistService {

	private final MtHelpRepository repository;
	
	@Override
	public void regist(MtHelp h) {

		repository.add(h);
		
	}
	

}
