package com.example.demo.form.mt;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MtHelpRegistForm {

	@Size(min=1, max=32, message="1文字から32文字で指定してください。")
	private String helpName;

//	@Size(min=1, max=64, message="1文字から64文字で指定してください。")//ここは金額で入れてもらえるようなバリデーションをかけるべき？
	private int helpAmomunt;  

}
