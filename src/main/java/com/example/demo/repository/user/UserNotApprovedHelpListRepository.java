package com.example.demo.repository.user;

import java.util.List;

import com.example.demo.entity.MtHelpNotApprove;

public interface UserNotApprovedHelpListRepository {
	
	//approved_help_listに承認済みのお手伝いを登録
	List<MtHelpNotApprove> selectByNameWildcard(String helptName);
	//not_approved_help_listのreported_flagを1にして報告済みお手伝いとして登録する。または2にして取り下げお手伝いとして登録。
	void notApprovedHelpadd(MtHelpNotApprove h);



}
