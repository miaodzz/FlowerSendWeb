<%@ page language="java" import="java.util.*,entity.*"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	HuayaPurchase hy = (HuayaPurchase) request.getAttribute("hy");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>花芽信息修改</title>

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

							<h4>花芽信息</h4>
							<div class="row">
								<div class="col-md-12">
									<form action="<%=basePath %>admin/huayaInUpdate" method="post">
									<input type="hidden" name="sid" value="<%=hy.getSerialnumber() %>"/>
									<table class="table table-hover">
									
										<tbody>
											<tr class="first">
												<td>处理号</td>
												<td><%=hy.getSerialnumber() %></td>
											</tr>
											<tr class="first">
												<td>用户id</td>
												<td><input type="text" class="col-md-5 form-control" name="uid"
													value="<%=hy.getUserId() %>" ></td>
											</tr>
											<tr class="first">
												<td>充值金额</td>
												<td><input type="text" class="col-md-5 form-control" name="money"
													value="<%=hy.getMoney() %>"></td>
											</tr>
											<tr class="first">
												<td>花芽数</td>
												<td><input type="text" class="col-md-5 form-control" name="amount"
													value="<%=hy.getAmount()%>"></td>
											</tr>
											<tr class="first">
												<td>充值时间</td>
												<td><%=hy.getTime() %></td>
											</tr>
											<tr>
												<td><input class="btn-flat small" type="submit" name="submit" value="确定修改">
												</td>
											</tr>
									</table>
									</form>>
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
