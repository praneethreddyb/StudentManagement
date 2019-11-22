package com.student.servlet;

import javax.servlet.annotation.WebServlet;

import com.student.base.BaseServlet;
import com.student.base.ControlKit;
import com.student.manager.ClassManager;
import com.student.util.FieldValidator;
import com.student.util.PageConstant;
@WebServlet("/do-class")
@SuppressWarnings("serial")
public class DoClassOperations extends BaseServlet {
	@Override
	protected void doAction(ControlKit controlKit) throws Exception {
		int id=0;
		try {
			id=controlKit.param("id")==null?0:Integer.parseInt(controlKit.param("id"));
		}catch (Exception e) {}
		String name = controlKit.param("name");
		ClassManager mgr = new ClassManager(controlKit.connection);
		if(id<=0) controlKit.resData.put("mode","Add"); else controlKit.resData.put("mode","Edit");
		String msg = FieldValidator.required(controlKit.reqData, null, "name");
		if (msg != null && !msg.trim().equals("")) {
			controlKit.resData.put("msg",msg);
			controlKit.page = PageConstant.CLASS_OPERATIONS;
			controlKit.layout=PageConstant.USER_LAYOUT;
			return;
		}
		if (id<=0) {
		 id = mgr.addingClass(controlKit.sessionUserId,name);
				controlKit.redirectUrl="./class-operations?id="+id;
		} else  {
			 mgr.updatingClassDetails(name,id);
				controlKit.redirectUrl = "./manage-class?msg=" + "Successfully student updated ...";
		}
	}

	@Override
	protected void decideForward(ControlKit controlKit) { 
			controlKit.page = PageConstant.CLASS_OPERATIONS;
			controlKit.layout=PageConstant.USER_LAYOUT;
	}
}
