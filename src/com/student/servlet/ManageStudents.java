package com.student.servlet;

import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;

import com.student.base.BaseServlet;
import com.student.base.ControlKit;
import com.student.manager.StudentManager;
import com.student.util.PageConstant;

@WebServlet("/manage-students")
public class ManageStudents extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doAction(ControlKit controlKit) throws Exception {
		StudentManager mgr = new StudentManager(controlKit.connection);
		List<Map<String, Object>> students = mgr.getStudentsList();
		controlKit.resData.put("students", students);
		controlKit.page = PageConstant.MANAGE_STUDENTS;
		controlKit.layout=PageConstant.USER_LAYOUT;
	}

	@Override
	protected void decideForward(ControlKit controlKit) {
		controlKit.page = PageConstant.MANAGE_STUDENTS;
		controlKit.layout=PageConstant.USER_LAYOUT;
	}

}
