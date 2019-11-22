package com.student.manager;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import com.student.dao.StudentDao;
import com.student.daoimplementation.StudentDaoImpl;

public class StudentManager {
	private StudentDao dao;

	public StudentManager(Connection connection) {
		this.dao = new StudentDaoImpl(connection);
	}

	public List<Map<String, Object>> getStudentsList() throws Exception {
		return dao.getStudentsList();
	}
	public Map<String,Object> checkStudentBelongToUser(int sessionUserId,int id) throws Exception{
		return dao.checkStudentBelongToUser(sessionUserId,id);
		
	}

	public int addingStudent(int sessionUserId, String name, String gender,String dob,String standard, String description,String[] hobbies) throws Exception {
		return dao.addingStudent(sessionUserId,name,gender,dob,standard,description, hobbies);
	}
	public int updatingStudentDetails(String name,String gender,String dob,String standard,String[] hobbies,int id) throws Exception {
		return dao.updatingStudentDetails(name, gender, dob, standard, id,hobbies);
	}
	public int deletingStudentDetails(int sessionUserId,int id) throws Exception {
		return dao.deletingStudentDetails(sessionUserId,id);
	}
}
