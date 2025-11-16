package com.example.demo.service.bankaccount;

import org.springframework.stereotype.Service;

import com.example.demo.entity.BankAccount;
import com.example.demo.repository.bankaccount.DepositAddRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepositAddServiceImpl implements DepositAddService {
	
	private final DepositAddRepository repository;
	
	@Override
	public void depositAdd(BankAccount ba) {

		repository.depositPlus(ba);

	}


}
