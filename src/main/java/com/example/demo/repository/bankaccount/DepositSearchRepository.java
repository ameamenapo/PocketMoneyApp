package com.example.demo.repository.bankaccount;

import java.util.List;

import com.example.demo.entity.BankAccount;

public interface DepositSearchRepository {
//	ユーザーIDによる預金額検索
	List<BankAccount> selectByDeposit(String userId);

}
