package com.student.servlet;

import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;

import com.student.base.BaseServlet;
import com.student.base.ControlKit;
import com.student.manager.HobbiesManager;
import com.student.util.PageConstant;

@SuppressWarnings("serial")
@WebServlet("/manage-hobbies")
public class ManageHobbies extends BaseServlet {

	@Override
	protected void doAction(ControlKit controlKit) throws Exception {
		HobbiesManager mgr = new HobbiesManager(controlKit.connection);
		List<Map<String, Object>> hobbies = mgr.getHobbiesList();
		controlKit.resData.put("hobbies", hobbies);
		controlKit.page = PageConstant.MANAGE_HOBBIES;
		controlKit.layout = PageConstant.USER_LAYOUT;

	}

	@Override
	protected void decideForward(ControlKit controlKit) {
		controlKit.page = PageConstant.MANAGE_HOBBIES;
		controlKit.layout = PageConstant.USER_LAYOUT;

	}

}
