package com.example.demo.repository.profile;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyPageImageRepositoryImpl implements MyPageImageRepository {

	private final JdbcTemplate jdbcTemplate;
	@Override
	public void add(String username) {

		String sql =
				" INSERT INTO user_image_mst " +
				" (user_id) " +
				" VALUES (?) ";	

		jdbcTemplate.update(sql, 
				username	);	
	}

	

}
