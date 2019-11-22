package com.student.servlet;

import javax.servlet.annotation.WebServlet;

import com.student.base.BaseServlet;
import com.student.base.ControlKit;
import com.student.manager.StudentManager;
import com.student.util.PageConstant;

@WebServlet("/do-studentdelete")
public class DoStudentDelete extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doAction(ControlKit controlKit) throws Exception {
		int id=controlKit.param("id")==null?0:Integer.parseInt(controlKit.param("id"));
		StudentManager mgr = new StudentManager(controlKit.connection);
		int student= mgr.deletingStudentDetails(controlKit.sessionUserId,id);
		if (student==1) {
			controlKit.redirectUrl = "./manage-students?msg=Successfully deleted";
		} else {
			controlKit.resData.put("msg", "Student doesn't belong to you");
			controlKit.page = PageConstant.MANAGE_STUDENTS;
			controlKit.layout=PageConstant.USER_LAYOUT;
		}
	}

	@Override
	protected void decideForward(ControlKit controlKit) {
		controlKit.page = PageConstant.MANAGE_STUDENTS;
		controlKit.layout=PageConstant.USER_LAYOUT;
	}

}
