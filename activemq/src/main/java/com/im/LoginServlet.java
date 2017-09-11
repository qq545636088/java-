package com.im;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    /**
     * Default constructor. 
     */
    public LoginServlet() {
    	System.out.println("进入   LoginServlet  ");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username =request.getParameter("username");
		String password = request.getParameter("password");
		if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
			request.getSession().setAttribute("error", "用户名或密码不能为空！");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		SmackUtil sm = new SmackUtil();
		boolean b = sm.init(username, password);
		if(b){
			List<User> userList =sm.getAllUser();
			request.getSession().setAttribute("userList", userList);
			response.sendRedirect("chat.jsp");
		}else{
			request.setAttribute("error", "用户名或密码错误！");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return ;
		}
	}

}
