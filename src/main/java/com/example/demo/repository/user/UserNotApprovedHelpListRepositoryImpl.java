package com.example.demo.repository.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.MtHelpNotApprove;
import com.example.demo.entity.User;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserNotApprovedHelpListRepositoryImpl implements UserNotApprovedHelpListRepository {
	
	private final JdbcTemplate jdbcTemplate;
	private final UserRepository userRepository;
	
	//部分検索 ※何も入力せず検索すると全検索になる
	@Override
	public List<MtHelpNotApprove> selectByNameWildcard(String helpName) {

		String sql = 
				" SELECT                                                 " + 
				"   not_approved_help_id,                                    " +
				"   reported_help_id,                                    " +
				"   help_id,                                    " +
				"   help_name,                                  " +
				"   help_ammount,                                    " +
				"   user_id,                                     " +
				"   reported_flag                                    " +
				" FROM                                                   " +
				"  	not_approved_help_list                                      " +
				" WHERE                                                  " +
				"  	help_name LIKE ?                            " +
				" ORDER BY                                               " +
				"   not_approved_help_id                                     ";

		String p = "%" + helpName + "%";	// プレースホルダの値
		
		// SQLで検索（プレースホルダ：p）
		List<Map<String, Object>> list 
				= jdbcTemplate.queryForList(sql, p);
		// 値の取得⇒結果の格納
		List<MtHelpNotApprove> result = new ArrayList<MtHelpNotApprove>(); // 結果の初期化
		for (Map<String, Object> one : list) {
			MtHelpNotApprove helpList = new MtHelpNotApprove();
			//2025/8/5追加
//			not_approved_help_listのreported_flagが0以外のレコードは差戻し一覧から除外			
//			not_approved_help_listのuser_idが現在ログインしているユーザー意外は差戻し一覧から除外
			
			//今ログインしているユーザidを取得
			String userName = SecurityContextHolder.getContext().getAuthentication().getName();
			//今ログインしているユーザレコードを取得
            User user = (User) userRepository.findByUserName(userName).get(0);
            //reported_flagが0以外かつユーザ名がログインユーザーと違うものは除外（ループを抜ける）
			if((int)one.get("reported_flag") != 0 && userName != user.getUserName()) {
				continue;
			}

			//not_approved_help_listのreported_flagが0かつログインユーザーのレコードならhelpListに詰めて差戻し一覧で表示
			helpList.setNot_approved_help_id((int)one.get("not_approved_help_id"));
			helpList.setReported_help_id((int)one.get("reported_help_id"));
			helpList.setHelp_id((int)one.get("help_id"));
			helpList.setHelp_name((String)one.get("help_name"));
			helpList.setHelp_ammount((int)one.get("help_ammount"));
			helpList.setUser_id((String)one.get("user_id"));
			helpList.setReported_flag((int)one.get("reported_flag"));
			System.out.println(helpList);
			result.add(helpList);
		}

		return result;
	}
	
	//not_approved_help_listに報告済み、または取り下げたお手伝いを登録
	@Override
	public void notApprovedHelpadd(MtHelpNotApprove h) {
		
		String sql =
				" UPDATE                  " + 
				"   not_approved_help_list          " + 
				" SET                     " + 
				"   reported_flag = ?      " + 
				" WHERE                   " + 
				"   not_approved_help_id = ?     ";
		
		jdbcTemplate.update(sql, 	
				h.getReported_flag(),
				h.getNot_approved_help_id()
							);
	
	};

}
