package com.student.base;

import java.sql.Connection;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControlKit {
	
	public String layout;
	public int sessionUserId;
	public boolean jsonResp = false;
	public String redirectUrl;
	public String page;
	public boolean ajaxCall=false;
	public Connection connection;
	public boolean callDoAction = true;
	public Map<String, Object> reqData;
	public Map<String, Object> resData;
	public Map<String, Object> sessionUser;
	public HttpServletRequest request;
	public HttpServletResponse response;

	@SuppressWarnings("unchecked")
	public ControlKit(Map<String, Object> reqData, Map<String, Object> resData, HttpServletRequest request,
			HttpServletResponse response) {
		super();
		this.jsonResp = false;
		this.reqData = reqData;
		this.resData = resData;
		this.request = request;
		this.response = response;
		this.sessionUser = (Map<String, Object>) request.getSession().getAttribute("sessionUser");
		if(this.sessionUser!=null) sessionUserId=Integer.parseInt(sessionUser.get("pk_id").toString());
		// System.out.println(sessionUser);
	}
	public void setSession(Map<String,Object> user) {
		request.getSession().setAttribute("sessionUser", user);
	}
	public String param(String key) {
		Object o = reqData.get(key);
		return o == null ? null : o.toString().trim();
	}
	public 	String[] params(String key) {
	String[] MultipleData = (String[]) reqData.get(key);
		return MultipleData == null ? null : MultipleData;
	}

}
