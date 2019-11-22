package com.student.dao;

import java.util.List;
import java.util.Map;

public interface HobbiesDao {
	List<Map<String, Object>> getHobbiesList() throws Exception;
	Map<String,Object> checkHobbyBelongToUser(int sessionUserId,int id) throws Exception;
	int addingHobby(int sessionUserId,String name) throws Exception;
	int updatingHobbyDetails(String name,int id) throws Exception;
	int deletingHobbyDetails(int sessionUserId,int id) throws Exception;
}
