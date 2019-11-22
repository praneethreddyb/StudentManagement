package com.student.dao;

import java.util.List;
import java.util.Map;

public interface ClassDao {
	List<Map<String, Object>> getClasstList() throws Exception;
	Map<String,Object> checkClassBelongToUser(int sessionUserId,int id) throws Exception;
	int addingClass(int sessionUserId,String name) throws Exception;
	int updatingClassDetails(String name,int id) throws Exception;
	int deletingClassDetails(int sessionUserId,int id) throws Exception;
}
