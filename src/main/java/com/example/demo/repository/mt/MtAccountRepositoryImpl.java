package com.example.demo.repository.mt;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.BankAccount;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MtAccountRepositoryImpl implements MtAccountRepository {

	private final JdbcTemplate jdbcTemplate;
	
	@Override
	public void add(BankAccount ba) {

		String sql =
				" INSERT INTO bank_account_mst " +
				" (user_id, financial_asset) " +
				" VALUES (?, ?) ";	

		jdbcTemplate.update(sql, 
				ba.getUser_id(),
				ba.getFinancial_asset()			);
		
	}

	

}
