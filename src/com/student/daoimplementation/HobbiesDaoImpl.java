package com.student.daoimplementation;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.student.dao.HobbiesDao;
import com.student.dao.util.DbUtil;
import com.student.dao.util.Queries;

public class HobbiesDaoImpl implements HobbiesDao {
	private Connection connection;

	public HobbiesDaoImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Map<String, Object>> getHobbiesList() throws Exception {
		return DbUtil.getMapList(connection, Queries.HOBBIES_LIST);
	}

	@Override
	public Map<String, Object> checkHobbyBelongToUser(int sessionUserId, int id) throws Exception {
		Map<String, Object> hobby = new LinkedHashMap<String, Object>();
		hobby = DbUtil.get(connection, Queries.HOBBIES_USER_VALIDATION, sessionUserId, id);
		return hobby;
	}

	@Override
	public int addingHobby(int sessionUserId, String name) throws Exception {
		return DbUtil.getGeneratedKey(connection, Queries.INSERT_HOBBIES, sessionUserId, name);
	}

	@Override
	public int updatingHobbyDetails(String name, int id) throws Exception {
		return DbUtil.update(connection, Queries.UPDATE_HOBBIES, name, id);
	}

	@Override
	public int deletingHobbyDetails(int sessionUserId,int id) throws Exception {
		if(checkHobbyBelongToUser(sessionUserId,id).isEmpty()){
			return 0;
		}else {
			if(!DbUtil.getMapList(connection,Queries.CHECK_STUDENTS_HAVE_HOBBY,id).isEmpty())
		    DbUtil.update(connection, Queries.DELETE_HOBBY_STUDENT_HOBBIES,id);
			return DbUtil.update(connection, Queries.DELETE_HOBBY_HOBBIES, id);
	}
	}
}
