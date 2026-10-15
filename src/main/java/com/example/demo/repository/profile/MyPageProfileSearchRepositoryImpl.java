package com.example.demo.repository.profile;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Profile;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor

public class MyPageProfileSearchRepositoryImpl implements MyPageProfileSearchRepository {
	private final JdbcTemplate jdbcTemplate;

	//useridからプロフィールを特定し、ヒットしたレコードを１つだけ取得
	@Override
	public Profile selectByUserId(String userId) {
		String sql = " SELECT                                                 " +
				"   profile_id,                                    " +
				"   user_id,                                     " +
				"   profile_name,                                     " +
				"   birth_year,                                     " +
				"   birth_month,                                    " +
				"   birth_day,                                    " +
				"   saving_goal,                                    " +
				"   what_i_want,                                    " +
				"   comments                                    " +
				" FROM                                                   " +
				"  profile_mst                                     " +
				" WHERE                                                  " +
				" user_id = ?                            ";

		String p = userId; // プレースホルダの値

		//findByUserId() の中で JdbcTemplate.queryForObject() を使っている場合、
		//DBに該当ユーザーIDが 存在しないと例外がスローされる（null にはならない）ので、try-catchで加工必要あり。
		try {
			// SQLで検索（プレースホルダ：p）
			Profile profile
					= jdbcTemplate.queryForObject(sql, 
																   	new BeanPropertyRowMapper<>(Profile.class),
																   	p);

			// 値の取得⇒結果の格納
			Profile userProfile = new Profile();
			userProfile.setProfile_id(profile.getProfile_id());
			userProfile.setUser_id(profile.getUser_id());
			userProfile.setProfile_name(profile.getProfile_name());
			userProfile.setBirth_year(profile.getBirth_year());
			userProfile.setBirth_month(profile.getBirth_month());
			userProfile.setBirth_day(profile.getBirth_day());
			userProfile.setSaving_goal(profile.getSaving_goal());
			userProfile.setWhat_i_want(profile.getWhat_i_want());
			userProfile.setComments(profile.getComments());
		
			return userProfile;
		} catch (EmptyResultDataAccessException e) {
			return null; // 0件のときはnullを返す
		}

	}

}
