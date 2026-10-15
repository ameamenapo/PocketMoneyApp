package com.example.demo.form.bankaccount;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class BankAccountForm {
	//bank_account_mstのカラム
	private int account_id;
    @Column(name = "user_id")
    private String userName;
	//private String user_id;
  
	private int financial_asset;

}
