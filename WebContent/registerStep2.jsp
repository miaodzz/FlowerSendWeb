<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*,entity.*,config.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	User user = (User) request.getAttribute("user");
	String uid = (String) request.getSession().getAttribute(Config.USER_ID);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="login-bg">
<head>
<title>修改个人资料，完成注册！</title>
<meta name="keywords" content="交友，婚恋，今日最佳，有趣，信息修改" />
<meta name="description" content="修改个人信息" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<jsp:include page="./static/must.jsp"></jsp:include>
<!-- 引入 jQuery -->

<!--添加datepicker支持-->
<script src="<%=basePath%>static/js/jquery.ui.datepicker.js"
	type="text/javascript"></script>
<!-- 或者引入jquery ui包，其中也包含对datepicker的支持  

  
    <!-- 添加中文支持-->
<script src="<%=basePath%>static/js/jquery.ui.datepicker-zh-CN.js"
	type="text/javascript"></script>


</head>
<body>
	<%
		String info = (String) request.getAttribute("info");
		if (info != null && !info.equals("")) {
			out.print("<script type=\"text/javascript\">alert(\"" + info + "\");</script>");
		}
	%>
	<div id="sidebar-nav">
	<ul id="dashboard-menu">
		<li class="active">
			<div class="pointer">
				<div class="arrow"></div>
				<div class="arrow_border"></div>
			</div> <a href="<%=basePath%>userPersonShow?uid=<%=request.getSession().getAttribute(Config.USER_ID) %>"> <i class="icon-home"></i> <span>个人中心</span>
		</a>
		</li>
		
		
		<li><a class="dropdown-toggle" href="#"> <i
				class="icon-user"></i> <span>我的资料</span> <i class="icon-chevron-down"></i>
		</a>
			<ul class="submenu">
				<li><a href="<%=basePath%>my/userInfoUpdate">资料修改</a></li>
				<li><a href="<%=basePath%>my/userPasswdUpdate.jsp">密码修改</a></li>
				<li><a href="<%=basePath%>my/userAvatarUpdate">头像修改</a></li>
				<li><a href="<%=basePath%>my/gallery">我的美照</a></li>
			</ul>
		</li>
		
		
		
		<li><a class="dropdown-toggle" href="#"> <i class="icon-group"></i>
				<span>礼尚往来</span> <i class="icon-chevron-down"></i>
		</a>
			<ul class="submenu">
				<li><a href="<%=basePath%>my/orderToMe">收礼记录</a></li>
			</ul>
		</li>
		
		
		
		
		<li><a class="dropdown-toggle" href="#"> <i
				class="icon-wrench"></i> <span>其它</span> <i
				class="icon-chevron-down"></i>
		</a>
			<ul class="submenu">
				<li><a href="<%=basePath%>logout">注销登录</a></li>
			</ul></li>
	</ul>
</div>
	<div class="content">


		<div id="pad-wrapper" class="user-profile">
			<!-- header -->
			<div class="col-md-9 bio">
				<div class="profile-box">
					<!-- biography -->

					<h4>个人资料</h4>
					<div class="row">
						<div class="col-md-12">
							<form method="post" action="<%=basePath%>my/userInfoUpdate">
								<input type="hidden" name="uid" id="uid"
									value="<%=user.getUserId()%>" />
								<table class="table table-hover">
									<tbody>
										<tr class="first">
											<td>id</td>
											<td><%=user.getUserId()%></td>
										</tr>

										<tr class="first">
											<td>密码</td>
											<td><a class="btn-flat small"
												href="<%=basePath%>my/userPasswdUpdate.jsp?uid=<%=user.getUserId()%>">设置密码</a>
											</td>
											<td>默认密码为您的电话号码</td>
										</tr>
										<tr class="first">
											<td>头像</td>
											<td><a href="<%=basePath%>my/userAvatarUpdate"
												class="name"> <img
													src="<%=(basePath + user.getAvatar())%>"
													class="img-circle avatar hidden-phone" />
											</a></td>
										</tr>
										<tr class="first">
											<td>昵称</td>
											<td><input type="text" class="col-md-9 form-control"
												id="nickname" name="nickname"
												value="<%=user.getNickname()%>" /></td>
										</tr>
										<tr class="first">
											<td>姓名</td>
											<td><input type="text" class="col-md-9 form-control"
												id="truename" name="truename"
												value="<%=user.getTruename()%>"></td>
										</tr>
										<tr class="first">
											<td>性别</td>
											<td><select class="ui-select" id="sex" name="sex">
													<option value="<%=user.getSex()%>"><%=user.getSex()%></option>
													<option value="<%=user.getSexOp()%>"><%=user.getSexOp()%></option>

											</select></td>
										</tr>
										<tr class="first">
											<td>生日</td>
											<td><input type="text" class="col-md-9 form-control"
												id="birthday" name="birthday"
												value="<%=user.getBirthday()%>" /></td>
											<td>输入的日期必须4位年-2位月-2位日</td>


										</tr>
										<tr class="first">
											<td>电话</td>
											<td><input type="text" class="col-md-9 form-control"
												id="telephone" name="telephone"
												value="<%=user.getTelephone()%>"></td>
											<td>一个电话号码只能绑定一个帐号</td>
										</tr>
										<tr class="first">
											<td>住址</td>
											<td><input type="text" class="col-md-9 form-control"
												id="address" name="address" value="<%=user.getAddress()%>"></td>
										</tr>
										<tr class="first">
											<td>个性签名</td>
											<td><textarea class="col-md-9 form-control" cols="50"
													rows="5" id="signature" name="signature">
													<%=user.getSignature()%></textarea></td>
										</tr>
										<tr>
											<td></td>
											<td><button class="btn-flat small" type="submit"
													value="Submit">确定修改</button></td>
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
	<script src="<%=basePath%>js/bootstrap-datepicker.js"></script>
	<script>
		$(document).ready(function() {
			$('#selectDate').datepicker();
		});
	</script>

</body>
</html>