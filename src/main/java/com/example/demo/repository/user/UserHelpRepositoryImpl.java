package com.example.demo.repository.user;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserHelp;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserHelpRepositoryImpl implements UserHelpRepository {

	private final JdbcTemplate jdbcTemplate;
	
	@Override
	public void add(UserHelp h) {

		String sql =
				" INSERT INTO user_help_list " +
				" (help_id, help_name, help_ammount, user_id, reported_flag) " +
				" VALUES (?, ?, ?, ?, ?) ";	

		jdbcTemplate.update(sql,
				h.getHelp_id(),
				h.getHelp_name(),
				h.getHelp_ammount(),
				h.getUser_id(),
				h.getReported_flag()			);
		
	}
	


}
