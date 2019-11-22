package com.student.servlet;

import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;

import com.student.base.BaseServlet;
import com.student.base.ControlKit;
import com.student.manager.ClassManager;
import com.student.util.PageConstant;

@SuppressWarnings("serial")
@WebServlet("/manage-class")
public class ManageClass extends BaseServlet {

	@Override
	protected void doAction(ControlKit controlKit) throws Exception {
		ClassManager mgr = new ClassManager(controlKit.connection);
		List<Map<String, Object>> standards = mgr.getClassList();
		controlKit.resData.put("standards", standards);
		controlKit.page = PageConstant.MANAGE_CLASS;
		controlKit.layout = PageConstant.USER_LAYOUT;

	}

	@Override
	protected void decideForward(ControlKit controlKit) {
		controlKit.page = PageConstant.MANAGE_CLASS;
		controlKit.layout = PageConstant.USER_LAYOUT;

	}

}
