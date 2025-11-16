package com.example.demo.repository.mt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.MtHelpReport;

import lombok.RequiredArgsConstructor;
@Repository
@RequiredArgsConstructor
public class MtReportedHelpListRepositoryImpl implements MtReportedHelpListRepository {
	


	private final JdbcTemplate jdbcTemplate;	
	//部分検索 ※何も入力せず検索すると全検索になる
	@Override
	public List<MtHelpReport> selectByNameWildcard(String helpName) {

		String sql = 
				" SELECT                                                 " + 
				"   reported_help_id,                                    " +
				"   help_id,                                    " +
				"   help_name,                                  " +
				"   help_ammount,                                    " +
				"   user_id,                                     " +
				"   approved_flag                                    " +
				" FROM                                                   " +
				"  reported_help_list                                     " +
				" WHERE                                                  " +
				"  	help_name LIKE ?                            " +
				" ORDER BY                                               " +
				"   reported_help_id                                     ";

		String p = "%" + helpName + "%";	// プレースホルダの値
		
		// SQLで検索（プレースホルダ：p）
		List<Map<String, Object>> list 
				= jdbcTemplate.queryForList(sql, p);
		// 値の取得⇒結果の格納
		List<MtHelpReport> result = new ArrayList<MtHelpReport>(); // 結果の初期化
		for (Map<String, Object> one : list) {
			MtHelpReport helpList = new MtHelpReport();
			//reported_help_listのapproved_flagが1または2である「承認済お手伝い」は報告されたお手伝い一覧から除外
			if((int)one.get("approved_flag") == 1 || (int)one.get("approved_flag") == 2) {
				continue;
			}
			//reported_help_listのapproved_flagが0ならhelpListに詰めて報告されたお手伝い一覧で表示
			helpList.setReported_help_id((int)one.get("reported_help_id"));
			helpList.setHelp_id((int)one.get("help_id"));
			helpList.setHelp_name((String)one.get("help_name"));
			helpList.setHelp_ammount((int)one.get("help_ammount"));
			helpList.setUser_id((String)one.get("user_id"));
			helpList.setApproved_flag((int)one.get("approved_flag"));
			result.add(helpList);
		}

		return result;
	}


//reported_help_listのapproved_flagを1にして承認済みお手伝いにする。
	@Override
	public void update(MtHelpReport h) {

		String sql =	
				" UPDATE                  " + 
				"   reported_help_list          " + 
				" SET                     " + 
				"   approved_flag = ?      " + 
				" WHERE                   " + 
				"   reported_help_id = ?     ";
		
		jdbcTemplate.update(sql, 			
				h.getApproved_flag(),
				h.getReported_help_id()
							);	
	}


}
