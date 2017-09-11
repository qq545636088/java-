package com.im;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChatServlet
 */
@WebServlet("/chatServlet")
public class ChatServlet extends HttpServlet {

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChatServlet() {
		System.out.println("进入 chatServlet");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = request.getParameter("message");
		String openfireId = request.getParameter("openfireId");
		SmackUtil.testChatManager(message, openfireId);
		System.out.println(SmackUtil.returnmessage + "---eeeee");
	}

}
