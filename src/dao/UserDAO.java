package dao;

import java.util.List;
import java.util.Map;

public interface UserDAO {
	public int insertUser(Map<String, String> user);
	public List<Map<String,String>> selectUserLsit(Map<String,String> user);
	public Map<String,String> selectUserById(String uiId, String uiPwd);
//	public Map<String, String> loginUser (String userId);
}
