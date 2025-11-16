package com.example.demo.service.mt;

import java.util.List;

import com.example.demo.entity.MtHelpReport;

public interface MtReportedHelpListService {
//	部分一致検索（名前）
	List<MtHelpReport> findByNameWildcard(String helpName);

//Myリストのフラグを1に登録
	void approve(MtHelpReport h);
	
//差戻し reported_help_listの承認フラグを2に登録
	void notApprove(MtHelpReport h);
}
