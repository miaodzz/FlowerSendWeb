<%@ page language="java" import="java.util.*,entity.*"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	SendInfo si = (SendInfo) request.getAttribute("sendinfo");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>派送信息修改</title>

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
	<div class="content">
		<div id="pad-wrapper" class="user-profile">
			<!-- header -->
			<div class="col-md-9 bio">
				<div class="profile-box">
					<!-- biography -->
					<div class="col-md-12 section">

						<h4>派送信息</h4>
						<div class="row">
							<div class="col-md-12">
								<form action="<%=basePath%>admin/sendInfoUpdate" method="post">
									<input type="hidden" name="sid" value="<%=si.getSendId()%>">
									<table class="table table-hover">
										<tbody>

											<tr class="first">
												<td>派送信息号</td>
												<td><%=si.getSendId()%></td>
											</tr>
											<tr class="first">
												<td>派送状态</td>
												<td><lable><input type="radio" checked="true"  name="state"
													value="已完成">已完成</lable>
													 <lable><input type="radio"  name="state"
													value="未派送">未派送</lable></td>
													
											</tr>
											<tr class="first">
												<td>派送人</td>
												<td><input type="text" class="col-md-5" name="sendman"
													value="<%=si.getSendman()%>"></td>
											</tr>
											<tr class="first">
												<td>派送时间</td>
												<td id="sendtime"><%=si.getSendTime()%></td>
											</tr>
											<tr>
												<td><input class="btn-flat small" type="submit"
													name="submit" value="保存修改"></td>
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

	<jsp:include page="footer.jsp"></jsp:include>
	<script type="text/javascript">
		$(document).ready(
				function(e) {
					function showTime() {
						var date = new Date();
						var s = date.getSeconds();
						var year = date.getFullYear();
						var month = date.getMonth() + 1;
						var day = date.getDate();
						var hour = date.getHours();
						var minute = date.getMinutes();
						var second = date.getSeconds();
						var dateStr = year + "年" + month + "月" + day + "日"
								+ hour + "时" + minute + "分" + s + "秒";
						$("#sendtime").html(dateStr);
					}
					var interval = window.setInterval(showTime, 100);
				});
	</script>


</body>
</html>