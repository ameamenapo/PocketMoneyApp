package com.example.demo.form.user;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserNotApprovedHelpListForm {
	//not_approved_help_listのカラム
	private int not_approved_help_id;
	private int reported_help_id;
	private int help_id;
	@Size(min=1, max=32, message="1文字から32文字で指定してください。")
	private String help_name;
//	@Size(min=1, max=64, message="1文字から64文字で指定してください。")//ここは金額で入れてもらえるようなバリデーションをかけるべき？
	private int help_ammount; 
	private String user_id;
	private int reported_flag;

}
