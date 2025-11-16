package com.example.demo.service.user;

import java.util.List;

import com.example.demo.entity.UserHelp;

public interface UserMyHelpListService {
//	部分一致検索（名前）
	List<UserHelp> findByNameWildcard(String helpName);

//Myリストのフラグを1に登録
	void regist(UserHelp h);
}
