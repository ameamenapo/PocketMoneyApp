package com.example.demo.repository.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.MtHelp;
import com.example.demo.entity.UserHelp;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
//public class HelpListRepositoryImpl  {
public class UserHelpListRepositoryImpl implements UserHelpListRepository {

	private final JdbcTemplate jdbcTemplate;	
	//部分検索 ※何も入力せず検索すると全検索になる
	// user-help-list.htmlに結果を返す
	@Override
	public List<MtHelp> selectByNameWildcard(String helpName) {

		String sql = 
				" SELECT                                                 " + 
				"   help_id,                                    " +
				"   help_name,                                  " +
				"   help_ammount                                     " +
				" FROM                                                   " +
				"  	help_mst                                      " +
				" WHERE                                                  " +
				"  	help_name LIKE ?                            " +
				" ORDER BY                                               " +
				"   help_id                                     ";
		
		String p = "%" + helpName + "%";	// プレースホルダの値
		
		// SQLで検索（プレースホルダ：p）
		List<Map<String, Object>> list 
				= jdbcTemplate.queryForList(sql, p);
		// 値の取得⇒結果の格納
		List<MtHelp> result = new ArrayList<MtHelp>(); // 結果の初期化
		for (Map<String, Object> one : list) {
			MtHelp helpList = new MtHelp();
			helpList.setHelp_id((int)one.get("help_id"));
			helpList.setHelp_name((String)one.get("help_name"));
			helpList.setHelp_ammount((int)one.get("help_ammount"));
			result.add(helpList);
		}

		return result;
	}

	//削除 
	// user-help-list.htmlでの削除ボタン押下時に使うSQL
	@Override
	public void delete(UserHelp h) {

		String sql =
				" DELETE              " + 
				" FROM                " + 
				"   user_help_list      " + 
				" WHERE               " + 
				"   user_help_id = ? "; 

		jdbcTemplate.update(sql, h.getUser_help_id());
		
	}

}
