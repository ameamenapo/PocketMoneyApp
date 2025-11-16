package com.example.demo.repository.user;

import com.example.demo.entity.UserHelp;
import com.example.demo.entity.UserHelpReport;

public interface UserHelpReportRepository {
	
	void add(UserHelp h);
	
	//reported_help_listにMyリストから削除したお手伝いを登録
	void reportedHelpAdd(UserHelpReport h);

}
