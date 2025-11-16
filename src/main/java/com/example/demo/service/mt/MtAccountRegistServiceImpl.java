package com.example.demo.service.mt;

import org.springframework.stereotype.Service;

import com.example.demo.entity.BankAccount;
import com.example.demo.repository.mt.MtAccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MtAccountRegistServiceImpl implements MtAccountRegistService {

	private final MtAccountRepository repository;
	
	@Override
	public void regist(BankAccount ba) {

		repository.add(ba);
		
	}
	

}
