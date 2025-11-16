package com.example.demo.repository.profile;

import com.example.demo.entity.Profile;

public interface MyPageProfileSearchRepository {
//	ユーザーIDによるプロフィール検索
	Profile selectByUserId(String userId);

}
