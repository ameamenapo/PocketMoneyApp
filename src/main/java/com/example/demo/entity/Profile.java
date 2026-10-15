package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
	//profile_mstのカラム
	private int profile_id;
	private String user_id;
	private String profile_name;
	private String birth_year;
	private String birth_month;
	private String birth_day;
	private String saving_goal;
	private String what_i_want;
	private String comments;
}
