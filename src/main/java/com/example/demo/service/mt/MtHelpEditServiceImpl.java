package com.example.demo.service.mt;

import org.springframework.stereotype.Service;

import com.example.demo.entity.MtHelp;
import com.example.demo.repository.mt.MtHelpListRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MtHelpEditServiceImpl implements MtHelpEditService {

	private final MtHelpListRepository repository;

	@Override
	public void edit(MtHelp h) {

		repository.update(h);

	}

}
