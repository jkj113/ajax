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
	private String selectUserList = "select * from user_info";
	private String loginUser = "select ui_id, ui_pwd,ui_name,ui_email from user_info where ui_id=? and ui_pwd=?";

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
//     public static void main(String[] args) {
//    	 UserDAO ud = new UserDAOImpl();
//    	 System.out.println(ud.loginUser("id"));
//    			 }
	
	@Override
	public List<Map<String, String>> selectUserLsit(Map<String, String> user) {
		List<Map<String,String>> userList = new ArrayList<>();
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(selectUserList);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Map<String,String> uList = new HashMap<>();
	     		uList.put("uiNum",rs.getString("ui_num"));
				uList.put("uiId",rs.getString("ui_id"));
				uList.put("uiPwd",rs.getString("ui_pwd"));
				uList.put("uiName",rs.getString("ui_name"));
				uList.put("uiEmail",rs.getString("ui_email"));
				userList.add(uList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}
//	@Override
//	public Map<String, String> loginUser(String userId) {
//		try {
//			PreparedStatement ps = DBCon.getCon().prepareStatement(loginUser);
//			ps.setString(1, userId);
//			ResultSet rs = ps.executeQuery();
//			while(rs.next()) {
//				Map<String,String> user = new HashMap<>();
//				user.put("uiId",rs.getString("ui_id"));
//				user.put("uiPwd",rs.getString("ui_pwd"));
//				return user;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//	}
//		return null;
//	}
	@Override
	public Map<String, String> selectUserById(String uiId, String uiPwd) {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(loginUser);
			ps.setString(1, uiId);
			ps.setString(2, uiPwd);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Map<String,String> user = new HashMap<>();
				user.put("uiId", rs.getString("ui_id"));
				user.put("uiName", rs.getString("ui_name"));
				user.put("uiPwd", rs.getString("ui_pwd"));
				user.put("uiEmail", rs.getString("ui_email"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCon.close();
		}
		return null;
	}
	
	}

