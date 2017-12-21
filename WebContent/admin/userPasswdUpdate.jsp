<%@ page language="java" import="java.util.*,entity.*"
	pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String uid=request.getParameter("uid");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="login-bg">
<head>
<title>管理员：修改用户密码</title>
<meta name="keywords" content="交友，婚恋，今日最佳，有趣" />
<meta name="description" content="用户列表" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<jsp:include page="../static/must.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="header.jsp" />
	<jsp:include page="left.jsp" />
	<% 
	String info=(String)request.getAttribute("info");
	if(info!=null&&!info.equals(""))
	{
		out.print("<script type=\"text/javascript\">alert(\""+info+"\");</script>");
	}
	%>
	<div class="content">

		<div id="pad-wrapper" class="user-profile">
			<!-- header -->
			<div class="row profile">
				<!-- bio, new note & orders column -->
				<div class="col-md-9 bio">
					<div class="profile-box">
						<!-- biography -->
						<div class="col-md-12 section">
							<h4>修改密码</h4>
							<div class="row">
								<div class="col-md-8">
								<form action="<%=basePath %>admin/userPasswdUpdate" method="post">
									<input type="hidden" name="uid" value="<%=uid %>"/> 
									
									<table class="table table-hover">
										<tbody>
											<tr class="first">
												<td class="col-md-4 sortable">用户id</td>
												<td><%=uid %></td>
											</tr>
										
											<tr class="first">
												<td class="col-md-4 sortable">请输入新密码：</td>
												<td><input  type="password"  class="col-md-5  form-control" id="pwd1" name="pwd1"></td>
											</tr>
											<tr class="first">
												<td class="col-md-4 sortable">请再次确认新密码：</td>
												<td><input type="password"  class=" form-control col-md-5" id="pwd2" name="pwd2"
													onkeyup="validate()"/></td>
											</tr>
											<tr>
												<td></td>
												<td><label id="tishi"> </label></td>
											</tr>
											<tr class="first">
												<td></td>
												<td><input type="submit" class="btn-glow primary   col-md-3"
													value="确定"/></td>

											</tr>
											<tr><td></td></tr>
											<tr class="first">
												<td></td>
												<td><a href="<%=basePath %>admin/userInfoUpdate?uid=<%=uid%>" class="name">放弃修改,回到用户信息修改页</a></td>"

											</tr>
										</tbody>
									</table>
								</form>
								</div>
							</div>


						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end main container -->


	<script type="text/javascript" src="<%=basePath%>js/md5.js"></script>
	<script>
		//对密码进行md5加密,赋给hidden  
		$("form").submit(function(e) {
			$("#pwd1").val(hex_md5($("#pwd1").val())); 
			$("#pwd2").val(hex_md5($("#pwd2").val()));
			//提交  
			$("#frm").submit();
		});
	</script>

	<script>
          function validate(){
              var pwd1 = document.getElementById("pwd1").value;
              var pwd2 = document.getElementById("pwd2").value;
				<!-- 对比两次输入的密码 -->
              if(pwd1 == pwd2) {
                  document.getElementById("tishi").innerHTML="<font color='green'>两次密码相同</font>";
                  document.getElementById("submit").disabled = false;
              }
              else {
                  document.getElementById("tishi").innerHTML="<font color='red'>两次密码不相同</font>";
                  document.getElementById("submit").disabled = true;
              }
          }
      </script>
</body>
</html>

