package com.example.demo.repository.bankaccount;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.BankAccount;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class DepositAddRepositoryImpl implements DepositAddRepository {
	
	private final JdbcTemplate jdbcTemplate;

	
	@Override
	public void depositPlus(BankAccount ba) {
		
		//reported_flagの更新
		String sql =
				" UPDATE                  " +
				"   bank_account_mst          " + 
				" SET                     " + 
				"   financial_asset = ?      " + 
				" WHERE                   " + 
				"   user_id = ?     ";
		
		jdbcTemplate.update(sql, 	
				ba.getFinancial_asset(),
				ba.getUser_id()

							);
	
	};

}
