package com.student.daoimplementation;

import java.sql.Connection;
import java.util.Map;
import com.student.dao.UserDao;
import com.student.dao.util.DbUtil;
import com.student.dao.util.Queries;

public class UserDaoImpl implements UserDao {
	private Connection connection;
	public UserDaoImpl(Connection connection) {
		this.connection=connection;
	}

	@Override
	public Map<String, Object> findUserByIdAndPwd(String loginId, String pwd)
			throws Exception {
		return DbUtil.get(connection, Queries.GET_STUDENT_BY_EMAIL_PASSWORD, loginId,pwd);
	}

 
}
