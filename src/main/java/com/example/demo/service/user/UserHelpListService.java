package com.example.demo.service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.MtHelp;

@Service
public interface UserHelpListService {

//	部分一致検索（名前）
	List<MtHelp> findByNameWildcard(String helpName);
}
