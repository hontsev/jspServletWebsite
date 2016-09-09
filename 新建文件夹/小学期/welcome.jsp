<%@ page language="java" contentType="text/html;charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>My JSP 'welcome.jsp' starting page</title>
  </head>
  
  <body>
    <%String username = (String)session.getAttribute("username"); %>
    welcome   <%= username %>
  </body>
</html>