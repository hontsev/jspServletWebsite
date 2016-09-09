package jspservlet.servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jspservlet.dao.UserDAO;
import jspservlet.vo.User;

import java.sql.PreparedStatement;
import java.sql.Connection;

public class LoginServlet extends HttpServlet {
	
	 public void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws IOException, ServletException{
		 HttpSession session=request.getSession();
		 if(session.getAttribute("username")!=null){
			 response.sendRedirect("./Usercenter.jsp");
		 }else{
			 response.sendRedirect("./LoginRegitration.jsp");
		 }
	 }
	//ÓÃ»§µÇÂ¼
	 public void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws IOException, ServletException{
		 User user = new User();
		 user.setUsername(request.getParameter("username"));
		 user.setPassword(request.getParameter("password"));
		 
		 UserDAO dao = new UserDAO();   
	     boolean flag = false;
	     try {
				flag = dao.queryByUsername(user);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} 
		 if(flag == true){   
			 HttpSession session=request.getSession();   
			 session.setAttribute("username", user.getUsername());   
	         response.sendRedirect("./Usercenter.jsp");
	    } else 
	        {   
	        	response.sendRedirect("./error.jsp");
	        }
	 }


}

	 