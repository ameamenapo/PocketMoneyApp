package com.example.demo.service.mt;

import org.springframework.stereotype.Service;

import com.example.demo.entity.MtHelp;
import com.example.demo.repository.mt.MtHelpListRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MtHelpRemoveServiceImpl implements MtHelpRemoveService {

	private final MtHelpListRepository repository;

	@Override
	public void remove(MtHelp h) {

		repository.delete(h);

	}

}
