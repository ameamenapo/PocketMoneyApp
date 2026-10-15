package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MtHelpReport {
	//reported_help_listのカラム
	private int reported_help_id;
	private int help_id;
	private String help_name;
	private int help_ammount;
	private String user_id;
	private int approved_flag;

}
