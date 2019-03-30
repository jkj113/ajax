package service.impl;

import java.util.List;
import java.util.Map;

import dao.UserDAO;
import dao.impl.UserDAOImpl;
import service.UserService;

public class UserServiceImpl implements UserService {
	private UserDAO udao = new UserDAOImpl();

	@Override
	public int insertUser(Map<String, String> user) {
		return udao.insertUser(user);
	}

	@Override
	public List<Map<String, String>> selectUserLsit(Map<String, String> user) {
		return udao.selectUserLsit(user);
	}

	@Override
	public Map<String, String> loginUser(String uiPwd) {
		return udao.loginUser(uiPwd);
	}
}

