package com.example.demo.service.user;

import java.util.List;

import com.example.demo.entity.MtHelpNotApprove;

public interface UserNotApprovedHelpListService {
//	部分一致検索（名前）
	List<MtHelpNotApprove> findByNameWildcard(String helpName);

//not_approved_help_listのフラグを1または2に登録
	void notapproveflagRegist(MtHelpNotApprove h);
}
