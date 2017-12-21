<%@ page language="java" import="java.util.*,entity.*"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	List<SendInfo> list = (List) request.getAttribute("sendInfoList");
	int countAll = (Integer) request.getAttribute("rows");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>派送信息列表</title>

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
		<div id="pad-wrapper" class="users-list">

			<div class="row header">
				<h4>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;派送信息</h4>
				<div class="col-md-10 col-sm-12 col-xs-12 pull-right">

					<!-- custom popup filter -->
					<!-- styles are located in css/elements.css -->
					<!-- script that enables this dropdown is located in js/theme.js -->
					<div class="ui-dropdown">

						<div class="head" data-toggle="tooltip" title="点我筛选">
							选择要查询的订单 <i class="arrow-down"></i>
						</div>
						<div class="dialog">
							<div class="pointer">
								<div class="arrow"></div>
								<div class="arrow_border"></div>
							</div>
							<div class="body">
								<p class="title">选择查询条件</p>
								<div class="form">
									<form action="<%=basePath %>admin/sendInfoList" method="get">
										请输入订单号： 
										<input class="search" size="20" type="text" id="kw"
											name="kw" /> <input type="submit" value="确定"
											class="btn-flat small" />
									</form>
								</div>

							</div>
						</div>
					</div>
					<h4>
						查询到派送信息<%=countAll%>条&nbsp;&nbsp;&nbsp;&nbsp;
					</h4>

				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<table class="table table-hover">
						<thead>
							<tr>
								<th class="col-md-2 sortable">派送信息号</th>
								<th class="col-md-2 sortable"><span class="line"></span>
									订单号</th>
								<th class="col-md-2 sortable"><span class="line"></span>
									派送状态</th>
								<th class="col-md-2 sortable"><span class="line"></span>
									派送时间</th>
								<th class="col-md-2 sortable"><span class="line"></span>
									派送人</th>
								<th class="col-md-2 sortable"><span class="line"></span> 操作</th>
							</tr>
						</thead>
						<tbody>
							<%
								for (SendInfo p : list) {
									out.println("<tr class=\"first\">");
									out.println("<td>" + p.getSendId() + "</td>");
									out.println("<td>" + p.getProductOrderId() + "</td>");
									out.println("<td>" + p.getSendState() + "</td>");
									out.println("<td>" + p.getSendTime() + "</td>");
									out.println("<td>" + p.getSendman() + "</td>");
										out.print("<td> <a class=\"btn-flat small\" href=\"" + basePath + "admin/sendInfoUpdate?sid="
											+ p.getSendId() + "\">设置派送</a></td>");
									out.println("</tr>");
								}
							%>

						</tbody>
					</table>
				</div>

			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
