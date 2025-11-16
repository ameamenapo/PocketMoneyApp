package com.example.demo.repository.mt;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.MtHelp;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MtHelpRepositoryImpl implements MtHelpRepository {

	private final JdbcTemplate jdbcTemplate;
	
	@Override
	public void add(MtHelp h) {

		String sql =
				" INSERT INTO help_mst " +
				" (help_name, help_ammount) " +
				" VALUES (?, ?) ";	

		jdbcTemplate.update(sql, 
				h.getHelp_name(),
				h.getHelp_ammount()			);
		
	}

	

}
