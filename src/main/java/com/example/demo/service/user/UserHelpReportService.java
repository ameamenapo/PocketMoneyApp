package com.example.demo.service.user;

import com.example.demo.entity.UserHelp;
import com.example.demo.entity.UserHelpReport;

public interface UserHelpReportService {
	
	void regist(UserHelp h);
	void reportedHelpRegist(UserHelpReport r);

}
