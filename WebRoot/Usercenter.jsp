<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Personal Centre</title>
<link href="css/style.css" type='text/css' rel="stylesheet">
<link href="css/bootstrap.css" type="text/css" rel="stylesheet"><!--��������CSS�ļ�-->
</head>

<body>
<div id="home" class="header">               <!--Ӧ��header��div-->
			  <div class="strip">
					 <div class="container">
						<p class="location"><strong>HongFu Compus of BUPT</strong></p><!--Ӧ��p��class ���ҼӴ�-->
						<p class="phonenum"> + 021 010XXXXXXXXX</p>
							<div class="clearfix"></div>
					</div>
				</div>
                		<div class="header-bottom two">  <!--Ӧ��header-bottom two��div-->
               
                        
			<div class="container">
				<div class="logo">
					<a href="home.jsp">                 <!--������home����-->
					<h1>Group  51</h1></a>               <!--����ֻ�ȥ-->
				</div>
				<span class="menu"></span>
				<div class="top-menu">
					<ul>
					<nav class="cl-effect-5">                  <!--������-->
						<li><a href="home.jsp"><span data-hover="Home">Home</span></a></li> <!--������ҳ-->
						<li>
                            <a class="active" href="companyInfo.jsp"><span data-hover="Company Info">Company Info</span>
                             </a><!--����companyinfo��ҳ�� ��ҳ-->
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
		<%String username = (String)session.getAttribute("username"); %>
		<h3>welcome   <%= username %> !</h3>
		</br>
		<a href="./logout">- Let me log out</a>
		</br>
		<a href="./submitShoppingcar">- My ShoppingCar</a>
		</br>
		<a href="./shoppinghistory">- My ShoppingHistory</a>
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
