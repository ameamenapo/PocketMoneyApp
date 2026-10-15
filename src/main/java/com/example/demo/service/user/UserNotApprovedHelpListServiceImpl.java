package com.example.demo.service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.MtHelpNotApprove;
import com.example.demo.repository.user.UserNotApprovedHelpListRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserNotApprovedHelpListServiceImpl implements UserNotApprovedHelpListService {
	
	private final UserNotApprovedHelpListRepository repository;
	
	//検索
	@Override
	public List<MtHelpNotApprove> findByNameWildcard(String helpName) {	

		List<MtHelpNotApprove> list
			= repository.selectByNameWildcard(helpName);
		
		return list;
	}
	
	//not_approved_help_listのフラグを1または2に登録
	@Override
	public void notapproveflagRegist(MtHelpNotApprove h) {

		repository.notApprovedHelpadd(h);
		
	}


}
