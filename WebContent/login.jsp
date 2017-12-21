<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>


<head>
<title>Flower:登录</title>
<meta name="keywords" content="交友，婚恋，今日最佳，有趣，管理员登陆" />
<meta name="description" content="管理员在这里登录" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<jsp:include page="./static/must.jsp"></jsp:include>
<!-- this page specific styles -->
<link rel="stylesheet"
	href="<%=basePath%>static/css/compiled/signin.css" type="text/css"
	media="screen" />

<style type="text/css">
body {
	background-image: url(<%=basePath%>static/img/bgs/8.jpg);
}
</style>
</head>
<body>
	<%
		String info = (String) request.getAttribute("info");
		if (info != null && !info.equals("")) {
			out.print("<script type=\"text/javascript\">alert(\"" + info + "\");</script>");
		}
	%>
	<header class="navbar navbar-inverse" role="banner">
	<div class="navbar-header">
		<button class="navbar-toggle" type="button" data-toggle="collapse"
			id="menu-toggler">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="<%=basePath%>index.jsp">Flower</a>
	</div>
	<ul class="nav navbar-nav hidden-xs">

		<li><a href="<%=basePath%>todayStar?s=0&m=1"
			class="hidden-xs hidden-sm">今日男神 </a></li>
		<li><a href="<%=basePath%>todayStar?s=0&m=0"
			class="hidden-xs hidden-sm">今日女神 </a></li>
		<li><a href="<%=basePath%>todayStar?s=1&m=1"
			class="hidden-xs hidden-sm">今日最壕男神 </a></li>
		<li><a href="<%=basePath%>todayStar?s=1&m=0"
			class="hidden-xs hidden-sm">今日最壕女神 </a></li>

	</ul>
	<ul class="nav navbar-nav pull-right hidden-xs">

		<li>
			<%
					out.print("<a href=\"" + basePath + "login.jsp\" class=\"hidden-xs hidden-sm\" >登录之后更精彩</a></li>");

				
			%>

		
		
	</ul>
	</header>
	<div class="login-wrapper">
		<!-- a href="index.html">
            <img class="logo" src="<%=basePath%>static/img/logo-white.png">
        </a>-->

		<div class="box">
			<div class="content-wrap">
				<h6>登录</h6>
				<form action="<%=basePath%>login" method="post" id="frm">
					<input type="hidden" id="pwd" name="pwd"> <input
						class="form-control" type="text" placeholder="电话号码" name="tel"
						id="tel" /> <input class="form-control" type="password"
						placeholder="密码" id="passwd" />
					<input
						class="btn-glow primary login" id="submit" type="submit"
						value="登录">
				</form>
				<div class="no-account">
					<p>还没账号?</p>
					<a href="register.jsp">注册</a>
				</div>
			</div>
		</div>


	</div>

	<script type="text/javascript" src="<%=basePath%>js/md5.js"></script>
	<script>
		//对密码进行md5加密,赋给hidden  
		$("form").submit(function(e) {
		
			var md5PWD = $("#passwd").val();
			$("#pwd").val(hex_md5(md5PWD));//不同的md5.js调用的方法可能不同 
			//提交  
			$("#frm").submit();
		});
	</script>



</body>
</html>