package com.example.demo.repository.bankaccount;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.BankAccount;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor

public class DepositSearchRepositoryImpl implements DepositSearchRepository {
	private final JdbcTemplate jdbcTemplate;	
	
	//部分検索 ※何も入力せず検索すると全検索になる
	@Override
	public List<BankAccount> selectByDeposit(String userId) {
	String sql = 
			" SELECT                                                 " + 
			"   account_id,                                    " +
			"   user_id,                                     " +
			"   financial_asset                                    " +
			" FROM                                                   " +
			"  bank_account_mst                                     " +
			" WHERE                                                  " +
			" user_id = ?                            ";

	String p = userId;	// プレースホルダの値
	
	// SQLで検索（プレースホルダ：p）
	List<Map<String, Object>> list 
			= jdbcTemplate.queryForList(sql, p);

	// 値の取得⇒結果の格納
	List<BankAccount> result = new ArrayList<BankAccount>(); 
	for (Map<String, Object> one : list) {
		BankAccount bankAccount = new BankAccount();
		bankAccount.setAccount_id((int)one.get("account_id"));
		bankAccount.setUser_id((String)one.get("user_id"));
		bankAccount.setFinancial_asset((int)one.get("financial_asset"));
		result.add(bankAccount);
	}

	return result;

	}


}
