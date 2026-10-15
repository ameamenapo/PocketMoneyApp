package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount {
	//bank_account_mstのカラム
	private int account_id;
	private String user_id;
	private int financial_asset;

}
