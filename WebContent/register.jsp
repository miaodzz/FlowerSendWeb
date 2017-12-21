<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>


<head>
<title>新用户注册</title>
<meta name="keywords" content="交友，婚恋，今日最佳，有趣" />
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
		<div class="box">
			<div class="content-wrap">
				<h6>注册</h6>
				<form action="<%=basePath%>register" method="post">
					<input class="form-control" type="text" placeholder="请输入电话号码"
						name="tel" id="tel"> <a class="name from-control" href="#">发送验证码</a>
					<input class="form-control" type="password" placeholder="请输入手机验证码"
						name="iden">
					<div>
						<lable>
						<input type="checkbox" checked="true" id="xieyi" name="xieyi" />我同意用户协议</lable>
					</div>
					<div class="action">
						<input type="submit" class="btn-glow primary signup" value="注册" />
					</div>
					<div class="already">
						<p>
							已经有帐号了? <a href="login.jsp">登录</a>
						</p>
					</div>
				</form>
			</div>
		</div>


	</div>
	<script>
		$("form").submit(function(e) {

			if(/^1[0-9]{10}$/.test($("#tel").val())) 
			{ 
				if ($('#xieyi').is(':checked')) {
					$("form").submit();
				} else {
					alert("需要同意用户协议!");
					return false;
				}
			} else{
				alert("输入电话号码不符合规范");
				return false;
			}
			
		
		});
	</script>

	<!-- scripts -->
	<script src="<%=basePath%>static/js/jquery-latest.js"></script>
	<script src="<%=basePath%>static/js/bootstrap.min.js"></script>
	<script src="<%=basePath%>static/js/theme.js"></script>
</body>
</html>