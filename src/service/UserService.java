package service;

import java.util.List;
import java.util.Map;

public interface UserService {
	public int insertUser(Map<String, String> user);
	public List<Map<String,String>> selectUserLsit(Map<String,String> user);
	public Map<String, String> loginUser (String uiPwd);
}
