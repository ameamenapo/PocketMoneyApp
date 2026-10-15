package com.example.demo.repository.user;

import java.util.List;

import com.example.demo.entity.UserHelp;

public interface UserMyHelpListRepository {
//	部分一致検索（名前） ※何も入力せずに検索すると全件検索
	List<UserHelp> selectByNameWildcard(String helptName);
//フラグを1に	
	void update(UserHelp h);
	
//削除
	void delete(UserHelp h);

}
