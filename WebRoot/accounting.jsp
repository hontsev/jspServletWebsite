<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Accounting </title>
<link href="css/style.css" type='text/css' rel="stylesheet">
<link href="css/bootstrap.css" type="text/css" rel="stylesheet"><!--连接两个CSS文件-->
</head>

<body>
<div id="home" class="header">               <!--应用header的div-->
			  <div class="strip">
					 <div class="container">
						<p class="location"><strong>HongFu Compus of BUPT</strong></p><!--应用p的class 并且加粗-->
						<p class="phonenum"> + 021 010XXXXXXXXX</p>
							<div class="clearfix"></div>
					</div>
				</div>
                		<div class="header-bottom two">  <!--应用header-bottom two的div-->
               
                        
			<div class="container">
				<div class="logo">
					<a href="home.jsp">                 <!--超链接home界面-->
					<h1>Group  51</h1></a>               <!--点击字回去-->
				</div>
				<span class="menu"></span>
				<div class="top-menu">
					<ul>
					<nav class="cl-effect-5">                  <!--导航栏-->
						<li><a href="home.jsp"><span data-hover="Home">Home</span></a></li> <!--返回主页-->
						<li>
                            <a class="active" href="companyInfo.jsp"><span data-hover="Company Info">Company Info</span>
                             </a><!--返回companyinfo的页面 本页-->
                        </li>
						<li><a href="productList.jsp"><span data-hover="Product List">Product List</span></a></li>
                       
					</nav>
					</ul>
				</div>
				<div class="clearfix">
                </div>
		   </div>
		</div>
	</div>
			         
<h3>You have put that product in waiting list! </h3> <h3>Please write your address, then the product would be delivered to your place.</h3>
<div>
<form method="post" action="./addGood">
	<p><span>  goodname</span>
    <%=(String)session.getAttribute("goodname") %>
	<input type="hidden" name="goodname" value="<%=(String)session.getAttribute("goodname") %>">
	</p>
    <p><span>  username</span>
    <%=(String)session.getAttribute("username") %>
	<input type="hidden" name="username" value="<%= (String)session.getAttribute("username")%>">
	</p>
	<p><span> address </span>
	<input type="text" name="address">
	</p>
	<input type="submit" value="submit">
</form>
    <!-- <a href="buySuccess.jsp"><img src="picture/submit.jpg" width="49" height="20" align="middle" /></a>  -->                                      
</div>
 


</body>
</html>
