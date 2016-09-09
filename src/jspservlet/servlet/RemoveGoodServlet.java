package jspservlet.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jspservlet.dao.ShoppingcarDAO;

public class RemoveGoodServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RemoveGoodServlet() {
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
		HttpSession session=request.getSession();
		 if(session.getAttribute("username")==null){
			 response.sendRedirect("./LoginRegitration.jsp");
			 return;
		 }else{
			 int goodId = Integer.parseInt(request.getParameter("id").toString());
			 ShoppingcarDAO dao = new ShoppingcarDAO();
			 dao.removeGood(goodId);
			 response.sendRedirect("./Usercenter.jsp");
		 }
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("./Usercenter.jsp");
	}

}
