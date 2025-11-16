package com.example.demo.repository.mt;

import java.util.List;

import com.example.demo.entity.MtHelpReport;

public interface MtReportedHelpListRepository {
//	部分一致検索（名前） ※何も入力せずに検索すると全件検索
	List<MtHelpReport> selectByNameWildcard(String helpName);
	//承認。フラグを1に	
	void update(MtHelpReport h);


}
