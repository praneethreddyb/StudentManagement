package com.student.manager;

import java.sql.Connection;
import java.util.Map;
import com.student.dao.UserDao;
import com.student.daoimplementation.UserDaoImpl;

public class UserManager {

	private UserDao dao;

	public UserManager(Connection connection) {
		this.dao = new UserDaoImpl(connection);
	}

	public Map<String, Object> findUserByIdAndPwd(String loginId, String pwd) throws Exception {
		return dao.findUserByIdAndPwd(loginId, pwd);
	}

}
