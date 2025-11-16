package com.example.demo.repository.user;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserHelp;
import com.example.demo.entity.UserHelpReport;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserHelpReportRepositoryImpl implements UserHelpReportRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	@Override
	public void add(UserHelp h) {
		//reported_flagの更新
		String sql =
				" UPDATE                  " + 
				"   user_help_list          " + 
				" SET                     " + 
				"   reported_flag = ?      " + 
				" WHERE                   " + 
				"   user_help_id = ?     ";
		
		jdbcTemplate.update(sql, 
				h.getUser_help_id(),
				h.getReported_flag()
							);
		
		
	}
	
	//reported_help_listにMyリストから削除したお手伝いを登録
	@Override
	public void reportedHelpAdd(UserHelpReport h) {
		
		String sql =
				" INSERT INTO reported_help_list " +
				" (help_id, help_name, help_ammount, user_id, approved_flag) " +
				" VALUES (?, ?, ?, ?, ?) ";	

		jdbcTemplate.update(sql,
				h.getHelp_id(),
				h.getHelp_name(),
				h.getHelp_ammount(),
				h.getUser_id(),
				h.getApproved_flag()			);
		
	};
	

}
