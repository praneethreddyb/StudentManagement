package com.student.manager;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.student.dao.HobbiesDao;
import com.student.daoimplementation.HobbiesDaoImpl;

public class HobbiesManager {
	private HobbiesDao dao;

	public HobbiesManager(Connection connection) {
		this.dao = new HobbiesDaoImpl(connection);
	}

	public List<Map<String, Object>> getHobbiesList() throws Exception {
		return dao.getHobbiesList();
	}
	public Map<String,Object> checkHobbyBelongToUser(int sessionUserId,int id) throws Exception{
		return dao.checkHobbyBelongToUser(sessionUserId,id);
		
	}
	public int addingHobbies(int sessionUserId, String name) throws Exception {
		return dao.addingHobby(sessionUserId,name);
	}
	public int updatingHobbyDetails(String name,int id) throws Exception {
		return dao.updatingHobbyDetails(name, id);
	}
	public int deletingHobbyDetails(int sessionUserId,int id) throws Exception {
		return dao.deletingHobbyDetails(sessionUserId,id);
	}

}