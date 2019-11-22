package com.student.dao;

import java.util.List;
import java.util.Map;

public interface StudentDao {
	List<Map<String, Object>> getStudentsList() throws Exception;
	Map<String,Object> checkStudentBelongToUser(int sessionUserId,int id) throws Exception;
	int addingStudent(int sessionUserId,String name,String gender,String dob,String standard, String description,String[] hobbies) throws Exception;
	int updatingStudentDetails(String name,String gender,String dob,String standard,int id,String[] hobbies) throws Exception;
	int deletingStudentDetails(int sessionUserId,int id) throws Exception;
}
