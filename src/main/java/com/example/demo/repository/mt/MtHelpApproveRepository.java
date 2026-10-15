package com.example.demo.repository.mt;

import com.example.demo.entity.MtHelpApprove;
import com.example.demo.entity.MtHelpNotApprove;

public interface MtHelpApproveRepository {
	
	//approved_help_listに承認済みのお手伝いを登録
	void approvedHelpAdd(MtHelpApprove h);
	//not_approved_help_listのreported_flagを0にして差し戻しお手伝いとして登録する。
	void notApprovedHelpadd(MtHelpNotApprove h);


}
