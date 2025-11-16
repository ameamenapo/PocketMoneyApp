package com.example.demo.service.bankaccount;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.BankAccount;
import com.example.demo.repository.bankaccount.DepositSearchRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepositSearchServiceImpl implements DepositSearchService {

	private final DepositSearchRepository repository;
	
	//口座預金検索
	@Override
	public List<BankAccount> findByDeposit(String userId) {	

		List<BankAccount> list
			= repository.selectByDeposit(userId);
		
		return list;
	}
	

}
