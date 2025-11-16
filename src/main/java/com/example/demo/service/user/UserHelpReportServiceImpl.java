package com.example.demo.service.user;

import org.springframework.stereotype.Service;

import com.example.demo.entity.UserHelp;
import com.example.demo.entity.UserHelpReport;
import com.example.demo.repository.user.UserHelpReportRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserHelpReportServiceImpl implements UserHelpReportService {
	
	private final UserHelpReportRepository repository;
	
	@Override
	public void regist(UserHelp h) {

		repository.add(h);
		
	}
	//reported_help_listにMyリストから削除したお手伝いを登録
	@Override
	public void reportedHelpRegist(UserHelpReport r) {

		repository.reportedHelpAdd(r);

	}

}
