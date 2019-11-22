package com.student.util;

import java.util.Arrays;

import com.student.base.ControlKit;

public class RequestValidatorForSession {
	private static final String NO_SESSION_URLS[] = { "/logout", "/index", "/do-login" };

	public static void validate(ControlKit controlKit) {
		String requestUri = controlKit.request.getServletPath();
		if (controlKit.sessionUser == null && !Arrays.asList(NO_SESSION_URLS).contains(requestUri)) {
			controlKit.callDoAction = false;
			controlKit.redirectUrl = "./index?msg=Session not exists or expired";
		} else if (controlKit.sessionUser != null && "/index".equals(requestUri)) {
			controlKit.callDoAction = false;
			controlKit.redirectUrl = "./manage-students?msg=Session already exists or loggedin";
		}
	}
}