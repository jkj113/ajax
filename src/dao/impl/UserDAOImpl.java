package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.UserDAO;
import db.DBCon;

public class UserDAOImpl implements UserDAO {
	private String insertUser = "insert into user_info(ui_num, ui_id, ui_pwd, ui_name, ui_email)"
			                  + " values(seq_ui_num.nextval,?,?,?,?)";
	private String UserList = "select ui_id, ui_pwd from user_info";

	@Override
	public int insertUser(Map<String, String> user) {	
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(insertUser);
			ps.setString(1, user.get("uiId"));
			ps.setString(2, user.get("uiPwd"));
			ps.setString(3, user.get("uiName"));
			ps.setString(4, user.get("uiEmail"));
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCon.close();
	}
		return 0;
	}
     public static void main(String[] args) {
    	 UserDAO ud = new UserDAOImpl();
    	 Map<String,String> user = new HashMap<>();
//    	 user.put("uiId","A");
//    	 user.put("uiPwd","2222");
//    	 user.put("uiName","아니이");
//    	 user.put("uiEmail","A@naver,com");
//    	 System.out.println(ud.insertUser(user));
    	 System.out.println(ud.userLsit(user));
    			 }
	@Override
	public List<Map<String, String>> userLsit(Map<String, String> user) {
		List<Map<String,String>> uList = new ArrayList();
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(UserList);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Map<String,String> u = new HashMap<>();
				u.put("uiId", rs.getString("ui_id"));
				u.put("uiPwd", rs.getString("ui_pwd"));
				uList.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uList;
	}
}
