package com.student.servlet;

import javax.servlet.annotation.WebServlet;

import com.student.base.BaseServlet;
import com.student.base.ControlKit;
import com.student.manager.ClassManager;
import com.student.manager.HobbiesManager;
import com.student.manager.StudentManager;
import com.student.util.FieldValidator;
import com.student.util.PageConstant;

@WebServlet("/do-student")
@SuppressWarnings("serial")
public class DoStudentOperations extends BaseServlet {
	@Override
	protected void doAction(ControlKit controlKit) throws Exception {
		int id = 0;
		try {
			id = controlKit.param("id") == null ? 0 : Integer.parseInt(controlKit.param("id"));
		} catch (Exception e) {
		}
		String name = controlKit.param("name");
		String standards = controlKit.param("standards");
		String dob = controlKit.param("dob");
		String gender = controlKit.param("gender");
		String[] hobbies = controlKit.params("hobbies");
		String description = controlKit.param("message");
		StudentManager mgr = new StudentManager(controlKit.connection);
		ClassManager cmgr = new ClassManager(controlKit.connection);
		controlKit.resData.put("standards", cmgr.getClassList());
		HobbiesManager hmgr = new HobbiesManager(controlKit.connection);
		controlKit.resData.put("hobbies", hmgr.getHobbiesList());
		if (id <= 0)
			controlKit.resData.put("mode", "Add");
		else
			controlKit.resData.put("mode", "Edit");
		if (FieldValidator.required(controlKit.reqData, null, "name", "standards", "dob", "gender") != null|| FieldValidator.dateValidation(dob) != null) {
			controlKit.resData.put("msg", "Invalid Credentials");
			controlKit.page = PageConstant.STUDENTS_OPERATIONS;
			controlKit.layout = PageConstant.USER_LAYOUT;
			return;
		}
		if (id <= 0) {
			id = mgr.addingStudent(controlKit.sessionUserId, name, gender, dob, standards, description, hobbies);
			if(id!=0) {
			controlKit.redirectUrl = "./student-operations?id=" + id;
			}else {
				controlKit.page = PageConstant.STUDENTS_OPERATIONS;
				controlKit.layout = PageConstant.USER_LAYOUT;
			}
		} else {
			mgr.updatingStudentDetails(name, gender, dob, standards,hobbies, id);
			controlKit.redirectUrl = "./manage-students?message=" + "Successfully student updated ...";
		}
	}

	@Override
	protected void decideForward(ControlKit controlKit) {
		controlKit.page = PageConstant.STUDENTS_OPERATIONS;
		controlKit.layout = PageConstant.USER_LAYOUT;
	}
}
