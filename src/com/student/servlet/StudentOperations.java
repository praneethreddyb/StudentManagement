package com.student.servlet;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;

import com.student.base.BaseServlet;
import com.student.base.ControlKit;
import com.student.manager.ClassManager;
import com.student.manager.HobbiesManager;
import com.student.manager.StudentManager;
import com.student.util.PageConstant;

@WebServlet("/student-operations")
@SuppressWarnings("serial")
public class StudentOperations extends BaseServlet {

	@Override
	protected void doAction(ControlKit controlKit) throws Exception {
		int id = 0;
		try {
			id = controlKit.param("id") == null ? 0 : Integer.parseInt(controlKit.param("id"));
		} catch (Exception e) {
		}
		Map<String, Object> student = new LinkedHashMap<String, Object>();
		String mode = controlKit.param("mode");
		if (id > 0) {
			StudentManager mgr = new StudentManager(controlKit.connection);
			student = mgr.checkStudentBelongToUser(controlKit.sessionUserId, id);
			if (student.isEmpty()) {
				controlKit.redirectUrl = "manage-students?msg=Invalid id";
				return;
			}
			if (!"edit".equalsIgnoreCase(mode)) {
				controlKit.ajaxCall=true;
				controlKit.page = PageConstant.STUDENTS_OPERATIONS;
				mode = "View";
			} else {
				mode = "Edit";
			}
		} else {
			mode = "Add";
		}
		controlKit.page = PageConstant.STUDENTS_OPERATIONS;
		controlKit.layout = PageConstant.USER_LAYOUT;
		controlKit.resData.put("mode", mode);
		controlKit.resData.put("student", student);
		ClassManager cmgr=new ClassManager(controlKit.connection);
		controlKit.resData.put("standards",cmgr.getClassList());
		HobbiesManager hmgr=new HobbiesManager(controlKit.connection);
		controlKit.resData.put("hobbies",hmgr.getHobbiesList());
	}

	@Override
	protected void decideForward(ControlKit controlKit) {
		controlKit.page = PageConstant.MANAGE_STUDENTS;
		controlKit.layout = PageConstant.USER_LAYOUT;
	}

}
