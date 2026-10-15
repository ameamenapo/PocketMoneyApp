package com.example.demo.service.mt;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.MtHelpReport;
import com.example.demo.repository.mt.MtReportedHelpListRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MtReportedHelpListServiceImpl implements MtReportedHelpListService {
	
	private final MtReportedHelpListRepository repository;
	
	//検索
	@Override
	public List<MtHelpReport> findByNameWildcard(String helpName) {	

		List<MtHelpReport> list
			= repository.selectByNameWildcard(helpName);
		
		return list;
	}
	
	//承認
	@Override
	public void approve(MtHelpReport h) {

		repository.update(h);
		
	}
	//差戻し
	@Override
	public void notApprove(MtHelpReport h) {

		repository.update(h);
		
	}

}
