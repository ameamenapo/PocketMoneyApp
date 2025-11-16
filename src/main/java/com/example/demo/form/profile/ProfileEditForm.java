package com.example.demo.form.profile;

import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class ProfileEditForm {

	private int profile_id; 
	private String user_id;
	@Size(max=32, message="32文字以内で指定してください。")
	private String profile_name;
    private String birth_year;
	private String birth_month;
	private String birth_day;
	@Size(min=1, max=32, message="1文字から32文字で指定してください。")
	private String saving_goal;
	@Size(min=1, max=32, message="1文字から32文字で指定してください。")
	private String what_i_want;
	@Size(min=1, max=200, message="1文字から200文字で指定してください。")
	private String comments;

}
