package com.example.demo.repository.profile;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserImage;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor

public class MyPageImageSearchRepositoryImpl implements MyPageImageSearchRepository {
	private final JdbcTemplate jdbcTemplate;	
	
	//useridから画像を特定し、ヒットしたレコードを１つだけ取得
	@Override
	public UserImage selectByUserId(String userId) {
	String sql = 
			" SELECT                                                 " + 
			"   image_id,                                    " +
			"   user_id,                                     " +
			"   image_data,                                     " +
			"   image_name,                                     " +
			"   image_type                                    " +
			" FROM                                                   " +
			"  user_image_mst                                     " +
			" WHERE                                                  " +
			" user_id = ?                            ";

	String p = userId;	// プレースホルダの値
	
	//findByUserId() の中で JdbcTemplate.queryForObject() を使っている場合、
	//DBに該当ユーザーIDが 存在しないと例外がスローされる（null にはならない）ので、try-catchで加工必要あり。
	try {
		// SQLで検索（プレースホルダ：p）
		UserImage image 
				= jdbcTemplate.queryForObject(sql, 
															   	new BeanPropertyRowMapper<>(UserImage.class),
															   	p);

		// 値の取得⇒結果の格納
		UserImage userImage = new UserImage();
		userImage.setImage_id(image.getImage_id());
		userImage.setUser_id(image.getUser_id());
		userImage.setImage_data(image.getImage_data());
		userImage.setImage_name(image.getImage_name());
		userImage.setImage_type(image.getImage_type());
	
		return userImage;
	} catch (EmptyResultDataAccessException e) {
		return null; // 0件のときはnullを返す
	}


	}


}
