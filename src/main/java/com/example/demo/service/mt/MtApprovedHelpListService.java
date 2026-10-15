package com.example.demo.service.mt;

import com.example.demo.entity.MtHelpApprove;
import com.example.demo.entity.MtHelpNotApprove;

public interface MtApprovedHelpListService {
	
	//承認
	void approvedHelpRegist(MtHelpApprove h);
	//差戻し
	void notApprovedHelpRegist(MtHelpNotApprove h);

}
