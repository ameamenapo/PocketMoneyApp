package com.example.demo.repository.mt;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.MtHelpApprove;
import com.example.demo.entity.MtHelpNotApprove;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MtHelpApproveRepositoryImpl implements MtHelpApproveRepository {
	
	private final JdbcTemplate jdbcTemplate;

	//approved_help_listに承認済みのお手伝いを登録
	@Override
	public void approvedHelpAdd(MtHelpApprove h) {
		
		String sql =
				" INSERT INTO approved_help_list " +
				" (reported_help_id, help_id, help_name, help_ammount, user_id) " +
				" VALUES (?, ?, ?, ?, ?) ";	

		jdbcTemplate.update(sql, 
				h.getReported_help_id(),
				h.getHelp_id(),
				h.getHelp_name(),
				h.getHelp_ammount(),
				h.getUser_id());
		
	};
	//not_approved_help_listに差戻したお手伝いを登録
	@Override
	public void notApprovedHelpadd(MtHelpNotApprove h) {
		
		String sql =
				" INSERT INTO not_approved_help_list " +
				" (reported_help_id, help_id, help_name, help_ammount, user_id, reported_flag) " +
				" VALUES (?, ?, ?, ?, ?, ?) ";	

		jdbcTemplate.update(sql, 
				h.getReported_help_id(),
				h.getHelp_id(),
				h.getHelp_name(),
				h.getHelp_ammount(),
				h.getUser_id(),
				h.getReported_flag());
		
	};
	


}
