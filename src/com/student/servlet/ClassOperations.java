package com.student.servlet;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;

import com.student.base.BaseServlet;
import com.student.base.ControlKit;
import com.student.manager.ClassManager;
import com.student.util.PageConstant;

@WebServlet("/class-operations")
@SuppressWarnings("serial")
public class ClassOperations extends BaseServlet {

	@Override
	protected void doAction(ControlKit controlKit) throws Exception {
		int id = 0;
		try {
			id = controlKit.param("id") == null ? 0 : Integer.parseInt(controlKit.param("id"));
		} catch (Exception e) {
		}
		Map<String, Object> standard = new LinkedHashMap<String, Object>();
		String mode = controlKit.param("mode");
		if (id > 0) {
			ClassManager mgr = new ClassManager(controlKit.connection);
			standard = mgr.getClassBelongingToUser(controlKit.sessionUserId, id);
			if (standard.isEmpty()) {
				controlKit.redirectUrl = "manage-class?msg=Invalid id";
				return;
			}
			if (!"edit".equalsIgnoreCase(mode)) {
				mode = "View";
			} else {
				mode = "Edit";
			}
		} else {
			mode = "Add";
		}
		controlKit.page = PageConstant.CLASS_OPERATIONS;
		controlKit.layout=PageConstant.USER_LAYOUT;
		controlKit.resData.put("mode", mode);
		controlKit.resData.put("standard", standard);
	}

	@Override
	protected void decideForward(ControlKit controlKit) {
		controlKit.page = PageConstant.MANAGE_CLASS;
		controlKit.layout = PageConstant.USER_LAYOUT;
	}

}
