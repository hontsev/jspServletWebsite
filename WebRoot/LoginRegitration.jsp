<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Personal Centre</title>
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
    <div class="container">
      	<div class="register">
			    <div class="container">
      	<div class="register">
            <div class="col-md-6 login-left">
		  	  <form method="post" action="./register"> 
					<h3>IF YOU ARE A NEW CUSTOMER </h3>
                    <h3>Regitration From</h3>
					 <div>
						<span>Username
						</span>
                           <input type="text" name="username"> 
					 </div>

				     <div class="register-bottom-grid">
						    <h3>Login Information</h3>
							 <div>
								<span>Password    
								</span>      
								<input type="text" name="password" >
							 </div>
							 <div>
								<span>Confirm Password</span>
								<input type="text" name="cpassword" >
							 </div>
							 <div class="clearfix"> </div>
					 </div>
					 <div class="register-but">
						<input type="submit" value="Register">
					 </div>
				</form>
				<div class="clearfix"> </div>
				
        
	    </div>
</div>    
			   <div class="col-md-6 login-right">
               <h3>IF YOU AREN'T NEW CUSTOMER</h3>
			  	<h3>Please log in</h3>
				<p>If you have an account with us, please log in.</p>
				<form method="post" action="./login"> 
				  <div>
					<span>Username</span>
					 <input type="text" name="username"> 
				  </div>
				  <div>
					<span>Password</span>
					<input type="text" name="password">
				  </div>
				  <input type="submit" value="Login">
			    </form>
			   </div>	
			   <div class="clearfix"> </div>
		</div>
	    </div>
    </div>
    </div>
    
    
    <!---- footer --->
			<div class="footer text-center">
				<div class="container">
					<div class="copy">
		              <p>&nbsp;</p>
		            </div>
					
				</div>
			</div>
    </body>
</body>
</html>
