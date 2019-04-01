package service;

import java.util.List;
import java.util.Map;

public interface UserService {
	public int insertUser(Map<String, String> user);
	public List<Map<String,String>> selectUserLsit(Map<String,String> user);
//	public Map<String, String> loginUser (String userId);
	public Map<String,String> login(String uiId, String uiPwd);
	// public Map<String, String> loginUser (String userId, String uiPwd);
}
