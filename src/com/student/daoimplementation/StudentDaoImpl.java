package com.student.daoimplementation;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.student.dao.StudentDao;
import com.student.dao.util.DbUtil;
import com.student.dao.util.Queries;

public class StudentDaoImpl implements StudentDao {
	private Connection connection;

	public StudentDaoImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Map<String, Object>> getStudentsList() throws Exception {
		return DbUtil.getMapList(connection, Queries.STUDENTS_LIST);
	}

	@Override
	public Map<String, Object> checkStudentBelongToUser(int sesssioUserId, int id) throws Exception {
		Map<String, Object> student = new LinkedHashMap<String, Object>();
		student = DbUtil.get(connection, Queries.STUDENT_USER_VALIDATION, sesssioUserId, id);
		return student;
	}

	@Override
	public int addingStudent(int sessionUserId, String name, String gender, String dob, String standard,
			String description, String[] hobbies) throws Exception {
		int key = DbUtil.getGeneratedKey(connection, Queries.INSERT_STUDENTS, sessionUserId, name, gender, dob,
				standard, description);
		if (hobbies != null) {
			DbUtil.executeBatchUpdate(connection, Queries.INSERT_STUDENT_HOBBIES, hobbies, key);
		}
		return key;
	}

	@Override
	public int updatingStudentDetails(String name, String gender, String dob, String standard, int id, String[] hobbies)
			throws Exception {
		int res = DbUtil.update(connection, Queries.UPDATE_STUDENTS, name, gender, dob, standard, id);
		List<Map<String, Object>> studentHobbiesList = DbUtil.getMapList(connection, Queries.STUDENT_HOBBIES_LIST, id);
		List<String> previousHobbies = new ArrayList<>();
		for (Map<String, Object> i : studentHobbiesList) {
			for (Map.Entry<String, Object> entry : i.entrySet()) {
				previousHobbies.add(entry.getValue().toString());
			}
		}
		if (studentHobbiesList.isEmpty()) {
			DbUtil.executeBatchUpdate(connection, Queries.INSERT_NEW_HOBBY, hobbies, id);
		} else if (hobbies == null) {
			DbUtil.executeBatchUpdate(connection, Queries.DELETE_HOBBIES_WHILE_UPDATION,
					(String[]) previousHobbies.toArray(), id);
		} else {
			List<String> currentHobbies = Arrays.asList(hobbies);
			List<String> newInserted = new ArrayList<>();
			for (String s : currentHobbies) {
				if (previousHobbies.contains(s)) {
					previousHobbies.remove(s);
				} else {
					newInserted.add(s);
				}
			}
			String[] convertPreviousToArray = previousHobbies.toArray(new String[previousHobbies.size()]);
			DbUtil.executeBatchUpdate(connection, Queries.DELETE_PREVIOUS_HOBBY, convertPreviousToArray, id);

			String[] convertCurrentToArray = newInserted.toArray(new String[newInserted.size()]);
			DbUtil.executeBatchUpdate(connection, Queries.INSERT_NEW_HOBBY, convertCurrentToArray, id);
		}
		return res;

	}

	@Override
	public int deletingStudentDetails(int sessionUserId, int id) throws Exception {
		if (checkStudentBelongToUser(sessionUserId, id).isEmpty()) {
			return 0;
		} else
			DbUtil.update(connection, Queries.DELETE_STUDENT_HOBBIES, id);
		return DbUtil.update(connection, Queries.DELETE_STUDENT, id);
	}
}
