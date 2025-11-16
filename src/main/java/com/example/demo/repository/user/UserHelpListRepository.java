package com.example.demo.repository.user;

import java.util.List;

import com.example.demo.entity.MtHelp;
import com.example.demo.entity.UserHelp;

public interface UserHelpListRepository {
//	部分一致検索（名前） ※何も入力せずに検索すると全件検索
	List<MtHelp> selectByNameWildcard(String helpName);

//削除
	void delete(UserHelp h);
}
