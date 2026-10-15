package com.example.demo.repository.profile;

import com.example.demo.entity.UserImage;

public interface MyPageImageSearchRepository {
//	ユーザーIDによる画像検索
	UserImage selectByUserId(String userId);

}
