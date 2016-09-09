<%@ page language="java" pageEncoding="GB18030"%>
 
<html> 
	<head>
		<title>JSP for UserForm form</title>
	</head>
	<body>
		<form method="post" action="./login"> 
			username : <input type="text" name="username"/><br/>
			password : <input type="text" name="password"/><br/>
			<input type="SUBMIT" name="submit" value="Submit"> 
			<input type="SUBMIT" name="register" value="Register" onclick="window.location.href'./Register.jsp'">
		</form>
	</body>
</html>

