package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserHelp {
	//user_help_listのカラム
	private int user_help_id;
	private int help_id;
	private String help_name;
	private int help_ammount;
	private String user_id;
	private int reported_flag;

}
