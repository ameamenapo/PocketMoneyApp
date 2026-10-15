package com.example.demo.repository.profile;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Profile;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyPageProfileRepositoryImpl implements MyPageProfileRepository {

	private final JdbcTemplate jdbcTemplate;

	@Override
	public void add(String username) {

		String sql = " INSERT INTO profile_mst " +
				" (user_id) " +
				" VALUES (?) ";

		jdbcTemplate.update(sql,
				username);
	}

	//更新
	@Override
	public void update(Profile p) {

		String sql = " UPDATE                  " +
				"   profile_mst          " +
				" SET                     " +
				"   profile_name = ? ,       " +
				"   birth_year = ? ,      " +
				"   birth_month = ? ,       " +
				"   birth_day = ? ,       " +
				"   saving_goal = ? ,      " +
				"   what_i_want = ? ,      " +
				"   comments = ?      " +
				" WHERE                   " +
				"   profile_id = ?     " +
				" AND                   " +
				"   user_id = ? ";

		jdbcTemplate.update(sql,
				p.getProfile_name(),
				p.getBirth_year(),
				p.getBirth_month(),
				p.getBirth_day(),
				p.getSaving_goal(),
				p.getWhat_i_want(),
				p.getComments(),
				p.getProfile_id(),
				p.getUser_id());
	}

}
