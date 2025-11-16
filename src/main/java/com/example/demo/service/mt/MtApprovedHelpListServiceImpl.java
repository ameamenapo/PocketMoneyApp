package com.example.demo.service.mt;

import org.springframework.stereotype.Service;

import com.example.demo.entity.MtHelpApprove;
import com.example.demo.entity.MtHelpNotApprove;
import com.example.demo.repository.mt.MtHelpApproveRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MtApprovedHelpListServiceImpl implements MtApprovedHelpListService {
	
	private final MtHelpApproveRepository repository;
	
	//approved_help_listに承認済みのお手伝いを登録
	@Override
	public void approvedHelpRegist(MtHelpApprove h) {

		repository.approvedHelpAdd(h);

	}
	//差し戻し
	@Override
	public void notApprovedHelpRegist(MtHelpNotApprove h) {

		repository.notApprovedHelpadd(h);
		
	}	


}
