package com.student.servlet;

import javax.servlet.annotation.WebServlet;
import com.student.base.BaseServlet;
import com.student.base.ControlKit;
import com.student.util.PageConstant;

@WebServlet("/index")
public class Login extends BaseServlet {

	private static final long serialVersionUID = 2360015804560189694L;

	@Override
	protected void doAction(ControlKit controlKit) throws Exception {
		controlKit.page = PageConstant.LOGIN_PAGE;
		controlKit.layout=PageConstant.GUEST_LAYOUT;
	}

	@Override
	protected void decideForward(ControlKit controlKit) {
		controlKit.page = PageConstant.LOGIN_PAGE;
		controlKit.layout=PageConstant.GUEST_LAYOUT;
	}

}
