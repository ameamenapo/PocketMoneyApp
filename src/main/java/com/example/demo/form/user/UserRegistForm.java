package com.example.demo.form.user;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistForm {
	//user_help_listのカラム
	private int user_help_id;
	private int help_id;
	@Size(min=1, max=32, message="1文字から32文字で指定してください。")
	private String help_name;
	private int help_ammount; 
	private String user_id;
	private int reported_flag;

}
