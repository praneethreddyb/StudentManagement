package com.student.manager;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import com.student.dao.ClassDao;
import com.student.daoimplementation.ClassDaoImpl;

public class ClassManager {
	private ClassDao dao;

	public ClassManager(Connection connection) {
		this.dao = new ClassDaoImpl(connection);
	}

	public List<Map<String, Object>> getClassList() throws Exception {
		return dao.getClasstList();
	}
	public Map<String,Object> getClassBelongingToUser(int sessionUserId,int id) throws Exception{
		return dao.checkClassBelongToUser(sessionUserId,id);
		
	}
	public int addingClass(int sessionUserId, String name) throws Exception {
		return dao.addingClass(sessionUserId,name);
	}
	public int updatingClassDetails(String name,int id) throws Exception {
		return dao.updatingClassDetails(name, id);
	}
	public int deletingClassDetails(int sessionUserId,int id) throws Exception {
		return dao.deletingClassDetails(sessionUserId,id);
	}

}