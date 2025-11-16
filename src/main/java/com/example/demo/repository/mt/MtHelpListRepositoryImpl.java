package com.example.demo.repository.mt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.MtHelp;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor

public class MtHelpListRepositoryImpl implements MtHelpListRepository {

	private final JdbcTemplate jdbcTemplate;	
	//部分検索 ※何も入力せず検索すると全検索になる
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
	//更新
	@Override
	public void update(MtHelp h) {

		String sql =
				" UPDATE                  " + 
				"   help_mst          " + 
				" SET                     " + 
				"   help_name = ? , " + 
				"   help_ammount = ?      " + 
				" WHERE                   " + 
				"   help_id = ?     ";
		
		jdbcTemplate.update(sql, 
							h.getHelp_name(),
							h.getHelp_ammount(),
							h.getHelp_id()		);
		
	}
	//削除
	@Override
	public void delete(MtHelp h) {

		String sql =
				" DELETE              " + 
				" FROM                " + 
				"   help_mst      " + 
				" WHERE               " + 
				"   help_id = ? "; 

		jdbcTemplate.update(sql, h.getHelp_id());
		
	}

}
