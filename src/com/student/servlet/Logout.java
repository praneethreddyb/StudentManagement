package com.student.servlet;

import javax.servlet.annotation.WebServlet;

import com.student.base.BaseServlet;
import com.student.base.ControlKit;
import com.student.util.PageConstant;

@WebServlet("/logout")
public class Logout extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doAction(ControlKit controlKit) throws Exception {
		controlKit.request.getSession().invalidate();
		controlKit.redirectUrl = "./index?msg=Successfully Logged Out";
	}

	@Override
	protected void decideForward(ControlKit controlKit) {
		controlKit.page = PageConstant.LOGIN_PAGE;
	}

}
