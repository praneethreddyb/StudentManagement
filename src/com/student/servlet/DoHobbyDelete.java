package com.student.servlet;

import javax.servlet.annotation.WebServlet;
import com.student.base.BaseServlet;
import com.student.base.ControlKit;
import com.student.manager.HobbiesManager;
import com.student.util.PageConstant;

@WebServlet("/do-hobbiesdelete")
public class DoHobbyDelete extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doAction(ControlKit controlKit) throws Exception {
		int id=controlKit.param("id")==null?0:Integer.parseInt(controlKit.param("id"));
		HobbiesManager mgr = new HobbiesManager(controlKit.connection);
		int result= mgr.deletingHobbyDetails(controlKit.sessionUserId,id);
		if (result==1) {
			controlKit.redirectUrl = "./manage-hobbies?msg=Successfully deleted";
		} else {
			controlKit.resData.put("msg", "Hobby doesn't belong to you");
			controlKit.page = PageConstant.MANAGE_HOBBIES;
			controlKit.layout=PageConstant.USER_LAYOUT;
		}
	}

	@Override
	protected void decideForward(ControlKit controlKit) {
		controlKit.page = PageConstant.MANAGE_HOBBIES;
		controlKit.layout=PageConstant.USER_LAYOUT;
	}

}
