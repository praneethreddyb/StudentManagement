package com.student.servlet;

import javax.servlet.annotation.WebServlet;

import com.student.base.BaseServlet;
import com.student.base.ControlKit;
import com.student.manager.ClassManager;
import com.student.util.PageConstant;

@WebServlet("/do-classdelete")
public class DoClassDelete extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doAction(ControlKit controlKit) throws Exception {
		int id = controlKit.param("id") == null ? 0 : Integer.parseInt(controlKit.param("id"));
		ClassManager mgr = new ClassManager(controlKit.connection);
		int result = mgr.deletingClassDetails(controlKit.sessionUserId, id);
		if (result == 1) {
			controlKit.redirectUrl = "./manage-class?msg=Successfully deleted";
		} else if(result==-1){
			controlKit.resData.put("msg", "Class doesn't belong to you");
			controlKit.page = PageConstant.MANAGE_CLASS;
			controlKit.layout = PageConstant.USER_LAYOUT;
		}else {
			controlKit.resData.put("msg", "Delete students before deleting class");
			controlKit.page = PageConstant.MANAGE_CLASS;
			controlKit.layout = PageConstant.USER_LAYOUT;
		}
	}

	@Override
	protected void decideForward(ControlKit controlKit) {
		controlKit.page = PageConstant.MANAGE_CLASS;
		controlKit.layout = PageConstant.USER_LAYOUT;
	}

}
