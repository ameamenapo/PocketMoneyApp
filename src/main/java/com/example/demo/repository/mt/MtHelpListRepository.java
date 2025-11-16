package com.example.demo.repository.mt;

import java.util.List;

import com.example.demo.entity.MtHelp;

public interface MtHelpListRepository {
//	部分一致検索（名前） ※何も入力せずに検索すると全件検索
	List<MtHelp> selectByNameWildcard(String helptName);
//編集
	void update(MtHelp h);
//削除
	void delete(MtHelp h);
}
