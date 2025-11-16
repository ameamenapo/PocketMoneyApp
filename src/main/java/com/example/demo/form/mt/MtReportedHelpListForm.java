package com.example.demo.form.mt;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MtReportedHelpListForm {
	//reported_help_listのカラム
	private int reported_help_id;
	private int help_id;
	@Size(min=1, max=32, message="1文字から32文字で指定してください。")
	private String help_name;
//	@Size(min=1, max=64, message="1文字から64文字で指定してください。")//ここは金額で入れてもらえるようなバリデーションをかけるべき？
	private int help_ammount;  
	private String user_id;
//	@Column(name = "user_id")
//	private String userName;
	private int approved_flag;

}
