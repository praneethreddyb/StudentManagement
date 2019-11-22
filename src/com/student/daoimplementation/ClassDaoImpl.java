package com.student.daoimplementation;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.student.dao.ClassDao;
import com.student.dao.util.DbUtil;
import com.student.dao.util.Queries;

public class ClassDaoImpl implements ClassDao {
	private Connection connection;

	public ClassDaoImpl(Connection connection) {
		this.connection = connection;
	}

	public List<Map<String, Object>> getClasstList() throws Exception {
		return DbUtil.getMapList(connection, Queries.CLASS_LIST);
	}

	@Override
	public Map<String, Object> checkClassBelongToUser(int sessionUserId, int id) throws Exception {
		Map<String, Object> standard = new LinkedHashMap<String, Object>();
		standard = DbUtil.get(connection, Queries.CLASS_USER_VALIDATION, sessionUserId, id);
		return standard;
	}

	@Override
	public int addingClass(int sessionUserId, String name) throws Exception {
		return DbUtil.getGeneratedKey(connection, Queries.INSERT_CLASS, sessionUserId, name);
	}

	@Override
	public int updatingClassDetails(String name, int id) throws Exception {
		return DbUtil.update(connection, Queries.UPDATE_CLASS, name, id);
	}

	@Override
	public int deletingClassDetails(int sessionUserId, int id) throws Exception {
		int result = 0;
		if (checkClassBelongToUser(sessionUserId, id).isEmpty()) {
			result=-1;
		}else if (DbUtil.getMapList(connection, Queries.CHECK_STUENTS_BEFORE_DELETING_CLASS, sessionUserId).isEmpty()) {
				result = DbUtil.update(connection, Queries.DELETE_CLASS, id);
			}
		return result;
	}
}
