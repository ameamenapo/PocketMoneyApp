package com.example.demo.service.bankaccount;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.BankAccount;

@Service
public interface DepositSearchService {

//	口座預金検索
	List<BankAccount> findByDeposit(String userId);
}
