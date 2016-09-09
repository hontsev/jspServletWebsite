package jspservlet.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jspservlet.dao.UserDAO;
import jspservlet.vo.User;

public class RegisterServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RegisterServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("./LoginRegitration.jsp");
		response.setContentType("text/html");
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDAO dao = new UserDAO();
		
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		//若两次密码不同，则提示错误信息
		if(!request.getParameter("cpassword").equals(request.getParameter("password"))){
			response.sendRedirect("./error.jsp");
			return;
		}
		boolean flag = false;
		try {
			flag = dao.userRegister(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		if(flag == true){   
			HttpSession session=request.getSession();   
	        session.setAttribute("username", user.getUsername());   
	        response.sendRedirect("./Usercenter.jsp");
	    }else {   
	    	response.sendRedirect("./error.jsp");
	    }
	}

}
