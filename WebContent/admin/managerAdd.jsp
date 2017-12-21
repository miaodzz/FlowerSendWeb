<%@ page language="java" import="java.util.*,entity.*"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>管理员增加</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<jsp:include page="../static/must.jsp" />
</head>

<body>

	<jsp:include page="header.jsp" />
	<jsp:include page="left.jsp" />
	<%
		String info = (String) request.getAttribute("info");
		if (info != null && !info.equals("")) {
			out.print("<script type=\"text/javascript\">alert(\"" + info + "\");</script>");
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

							<h4>管理员增加</h4>
							<div class="row">
								<div class="col-md-12">
									<form action="<%=basePath%>admin/managerAdd" method="post">
										
										<table class="table table-hover">

											<tbody>
												<tr class="first">
													<td>输入新增管理员id或电话号码</td>
													<td><input type="text" class="col-md-5 form-control"
														name="uid"></td>
												</tr>
												
												<tr>
													<td><input class="btn-flat small" type="submit"
														name="submit" value="确定"></td>
												</tr>
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

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>