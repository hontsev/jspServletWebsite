package jspservlet.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jspservlet.dao.ShoppingcarDAO;
import jspservlet.vo.Good;

public class AddGoodServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddGoodServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String goodName = request.getParameter("goodname");
		HttpSession session=request.getSession();
		session.setAttribute("goodname", goodName);
		 if(session.getAttribute("username")!=null){
			 response.sendRedirect("./accounting.jsp");
		 }else{
			 response.sendRedirect("./LoginRegitration.jsp");
		 }
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("username");
		String goodName = request.getParameter("goodname");
		String address = request.getParameter("address");
		Good good = new Good();
		good.setName(goodName);
		good.setUserAddress(address);
		good.setUserName(userName);
		ShoppingcarDAO dao = new ShoppingcarDAO();
		boolean flag = false;
		flag = dao.addGood(good);
		if(flag == true){
			response.sendRedirect("./addSuccess.jsp");
		}else{
			response.sendRedirect("./error.jsp");
		}
	}

}
