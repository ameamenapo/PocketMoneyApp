package com.example.demo.repository.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserHelp;

import lombok.RequiredArgsConstructor;
@Repository
@RequiredArgsConstructor
public class UserMyHelpListRepositoryImpl implements UserMyHelpListRepository {
	
	private final JdbcTemplate jdbcTemplate;	
	//部分検索 ※何も入力せず検索すると全検索になる
	@Override
	public List<UserHelp> selectByNameWildcard(String helpName) {

		String sql = 
				" SELECT                                                 " + 
				"   user_help_id,                                    " +
				"   help_id,                                    " +
				"   help_name,                                  " +
				"   help_ammount,                                    " +
				"   user_id,                                     " +
				"   reported_flag                                    " +
				" FROM                                                   " +
				"  	user_help_list                                      " +
				" WHERE                                                  " +
				"  	help_name LIKE ?                            " +
				" ORDER BY                                               " +
				"   user_help_id                                     ";

		String p = "%" + helpName + "%";	// プレースホルダの値
		
		// SQLで検索（プレースホルダ：p）
		List<Map<String, Object>> list 
				= jdbcTemplate.queryForList(sql, p);
		// 値の取得⇒結果の格納
		List<UserHelp> result = new ArrayList<UserHelp>(); // 結果の初期化
		for (Map<String, Object> one : list) {
			UserHelp helpList = new UserHelp();
			//user_help_listのuser_idが現在のユーザー以外はMyリストから除外
			//ログインユーザIDをusernameとして取得
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			if(!(one.get("user_id").equals(username))) {
				continue;
			}
			
			//user_help_listのreported_flagが1である「報告済お手伝い」はMyリストから除外
			if((int)one.get("reported_flag") == 1) {
				continue;
			}

		
			//user_help_listのreported_flagが0ならhelpListに詰めてMyリストで表示
			helpList.setUser_help_id((int)one.get("user_help_id"));
			helpList.setHelp_id((int)one.get("help_id"));
			helpList.setHelp_name((String)one.get("help_name"));
			helpList.setHelp_ammount((int)one.get("help_ammount"));
			helpList.setUser_id((String)one.get("user_id"));
			helpList.setReported_flag((int)one.get("reported_flag"));
//			System.out.println(helpList);
			result.add(helpList);
		}

		return result;
	}

	//削除
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

//フラグを1に
	@Override
	public void update(UserHelp h) {

		String sql =
				" UPDATE                  " + 
				"   user_help_list          " + 
				" SET                     " + 
				"   reported_flag = ?      " + 
				" WHERE                   " + 
				"   user_help_id = ?     ";
		
		jdbcTemplate.update(sql, 	
				h.getReported_flag(),
				h.getUser_help_id()
							);
		
		
	}


}
