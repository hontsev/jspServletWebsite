package jspservlet.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspservlet.dao.ShoppingcarDAO;
import jspservlet.vo.Good;
import jspservlet.vo.User;

public class SubmitShoppingcarServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SubmitShoppingcarServlet() {
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
		//把用户的购物车打印出来
		ShoppingcarDAO dao = new ShoppingcarDAO();
		User user = new User();
		user.setUsername(request.getSession().getAttribute("username").toString());
		Good[] goods = dao.getUserShoppingcar(user);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /><title>Accounting </title><link href=\"css/style.css\" type='text/css' rel=\"stylesheet\"><link href=\"css/bootstrap.css\" type=\"text/css\" rel=\"stylesheet\"></head><body><div id=\"home\" class=\"header\"><div class=\"strip\"> <div class=\"container\"><p class=\"location\"><strong>HongFu Compus of BUPT</strong></p><p class=\"phonenum\"> + 021 010XXXXXXXXX</p><div class=\"clearfix\"></div></div></div><div class=\"header-bottom two\"><div class=\"container\"><div class=\"logo\"><a href=\"home.jsp\"><h1>Group  51</h1></a></div><span class=\"menu\"></span><div class=\"top-menu\"><ul><nav class=\"cl-effect-5\"><li><a href=\"home.jsp\"><span data-hover=\"Home\">Home</span></a></li><li><a class=\"active\" href=\"companyInfo.jsp\"><span data-hover=\"Company Info\">Company Info</span> </a></li><li><a href=\"productList.jsp\"><span data-hover=\"Product List\">Product List</span></a></li></nav></ul></div><div class=\"clearfix\"></div></div></div></div>");
		out.println("<h3>ShoppingCar</h3>");
		if(goods != null){
			out.println("</br></hr><table border='1'><tr><th>No</th><th>ProductName</th><th>UserAddress</th><th>Operation</th></tr>");
			for(int i=0;i<goods.length;i++){
				if(goods[i]!=null){
					out.println("<tr>");
					out.println("<td>"+(i+1)+"</td>");
					out.println("<td>"+goods[i].getName()+"</td>");
					out.println("<td>"+goods[i].getUserAddress()+"</td>");
					out.println("<td>"+"<a href='./removeGood?id="+String.valueOf(goods[i].getId())+"'>Remove</a>"+"</td>");
					out.println("</tr>");
				}
			}
			out.println("</table></hr><form method='post' action='./submitShoppingcar'><input type='submit' value='submit'/></form>");
		}else{
			out.println("no good.");
		}
		out.println("</body></html>");
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean flag = false;
		ShoppingcarDAO dao = new ShoppingcarDAO();
		User user = new User();
		user.setUsername(request.getSession().getAttribute("username").toString());
		flag = dao.submitShoppingcar(user);
		if(flag == true){
			response.sendRedirect("./buySuccess.jsp");
		}else{
			response.sendRedirect("./error.jsp");
		}
	}

}
