package com.student.servlet;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;

import com.student.base.BaseServlet;
import com.student.base.ControlKit;
import com.student.manager.HobbiesManager;
import com.student.util.PageConstant;

@WebServlet("/hobbies-operations")
@SuppressWarnings("serial")
public class HobbiesOperations extends BaseServlet {

	@Override
	protected void doAction(ControlKit controlKit) throws Exception {
		int id = 0;
		try {
			id = controlKit.param("id") == null ? 0 : Integer.parseInt(controlKit.param("id"));
		} catch (Exception e) {
		}
		Map<String, Object> hobbies = new LinkedHashMap<String, Object>();
		String mode = controlKit.param("mode");
		if (id > 0) {
			HobbiesManager mgr = new HobbiesManager(controlKit.connection);
			hobbies = mgr.checkHobbyBelongToUser(controlKit.sessionUserId, id);
			if (hobbies.isEmpty()) {
				controlKit.redirectUrl = "manage-hobbies?msg=Invalid id";
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
		controlKit.page = PageConstant.HOBBIES_OPERATIONS;
		controlKit.layout=PageConstant.USER_LAYOUT;
		controlKit.resData.put("mode", mode);
		controlKit.resData.put("hobbies", hobbies);
	}

	@Override
	protected void decideForward(ControlKit controlKit) {
		controlKit.page = PageConstant.MANAGE_HOBBIES;
		controlKit.layout = PageConstant.USER_LAYOUT;
	}

}
