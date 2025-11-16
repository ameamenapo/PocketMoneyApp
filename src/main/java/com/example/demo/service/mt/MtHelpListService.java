package com.example.demo.service.mt;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.MtHelp;

@Service
public interface MtHelpListService {

//	部分一致検索（名前）
	List<MtHelp> findByNameWildcard(String helpName);
}
