package com.student.base;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.student.dao.util.DbUtil;
import com.student.util.RequestValidatorForSession;

@SuppressWarnings("serial")
public abstract class BaseServlet extends HttpServlet {

	public BaseServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		Map<String, Object> reqData = new LinkedHashMap<String, Object>();
		Map<String, Object> resData = new LinkedHashMap<String, Object>();
		for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
			reqData.put(entry.getKey(),
					entry.getValue() != null ? (entry.getValue().length == 1 ? entry.getValue()[0] : entry.getValue())
							: null);
		}
		ControlKit controlKit = new ControlKit(reqData, resData, request, response);
		RequestValidatorForSession.validate(controlKit);
		if (controlKit.callDoAction) {
			decideForward(controlKit);
			// Get Connection and assign to controlKit
			try {
				controlKit.connection = DbUtil.getConnection();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				controlKit.connection = null;
			}
			if (controlKit.connection == null) {
				controlKit.callDoAction = false;
				controlKit.resData.put("msg", "Db issue, Please contact system administrator");

			}
		}
		if (controlKit.callDoAction) {
			try {
				doAction(controlKit);
			} catch (ServletException e) {
				throw e;
			} catch (IOException e) {
				throw e;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (controlKit.connection != null) {
					// Todo: commit if auto commit is false and not closed and close connection if
					// connection is not closed
					try {
						if (!controlKit.connection.isClosed()) {
							if (!controlKit.connection.getAutoCommit()) {
								controlKit.connection.commit();
							}
							controlKit.connection.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		resData.put("layout", controlKit.layout);
		resData.put("jsonResp", controlKit.jsonResp);
		resData.put("redirectUrl", controlKit.redirectUrl);
		resData.put("page", controlKit.page);
		for (Map.Entry<String, Object> entry : controlKit.resData.entrySet())
			request.setAttribute(entry.getKey(), entry.getValue());
		request.setAttribute("resData", resData);
		if (controlKit.jsonResp) {
			// TODO: need to write code here
		} else if (controlKit.redirectUrl != null && !controlKit.redirectUrl.trim().equals("")) {
			response.sendRedirect(controlKit.redirectUrl);// request.getContextPath() +
		} else if(controlKit.ajaxCall==true){
			request.getRequestDispatcher(controlKit.page).forward(request, response); // "pages/layout/guestLayout.jsp"
		}else {
			request.getRequestDispatcher(controlKit.layout).forward(request, response); 
		}
	}

	protected abstract void doAction(ControlKit controlKit) throws Exception;

	protected abstract void decideForward(ControlKit controlKit);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}