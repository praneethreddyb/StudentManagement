package com.student.servlet;

import java.util.Map;
import javax.servlet.annotation.WebServlet;
import com.student.base.BaseServlet;
import com.student.base.ControlKit;
import com.student.manager.UserManager;
import com.student.util.PageConstant;
import com.student.util.FieldValidator;

@WebServlet("/do-login")
public class DoLogin extends BaseServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doAction(ControlKit controlKit) throws Exception {
		String msg = FieldValidator.required(controlKit.reqData, null, "user", "password");
		if (msg != null && !msg.trim().equals("")) {
			controlKit.resData.put("msg", msg);
			controlKit.page = PageConstant.LOGIN_PAGE;
		} else {
			UserManager mgr = new UserManager(controlKit.connection);
			Map<String, Object> user = mgr.findUserByIdAndPwd(controlKit.param("user"), controlKit.param("password"));
			if (user==null || user.isEmpty()) {
				controlKit.resData.put("msg", "Enter correct details");
				controlKit.page = PageConstant.LOGIN_PAGE;
				controlKit.layout=PageConstant.GUEST_LAYOUT;
			} else {
				controlKit.setSession(user);
				controlKit.redirectUrl = "./manage-students?msg=Successfully Logged In";
			}
		}
	}

	@Override
	protected void decideForward(ControlKit controlKit) {
		controlKit.page = PageConstant.LOGIN_PAGE;
		controlKit.layout=PageConstant.GUEST_LAYOUT;
	}
}
