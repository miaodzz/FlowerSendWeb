<%@ page language="java" import="java.util.*,entity.*"
	pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	Map list = (Map) request.getAttribute("productordermap");
	int rows = list.size();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="login-bg">
<head>
<title>我的收礼</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<jsp:include page="../static/must.jsp"></jsp:include>
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
		<div id="pad-wrapper" class="users-list">
			<div class="row header">
				<h4>我送出的礼物</h4>
				<div class="col-md-10 col-sm-12 col-xs-12 pull-right">

					<!-- custom popup filter -->
					<!-- styles are located in css/elements.css -->
					<!-- script that enables this dropdown is located in js/theme.js -->
					<div class="ui-dropdown">

						<div class="head" data-toggle="tooltip">
							选择排序方式<i class="arrow-down"></i>
						</div>
						<div class="dialog">
							<div class="pointer">
								<div class="arrow"></div>
								<div class="arrow_border"></div>
							</div>
							<div class="body">
								<p class="title">选择排序方式</p>
								<div class="form">
									<form action="orderByMe" method="get">
										<select class="ui-select" id="key" name="key">
											<option value="purchase_time">时间</option>
											<option value="count_price">金额</option>
											<option value="count_amount">数量</option>
										</select> <select class="ui-select" id="r" name="r">
											<option value="asc">正序</option>
											<option value="desc">逆序</option>
										</select> <input type="submit" class="btn-flat small" />
									</form>
								</div>

							</div>
						</div>
					</div>

					<h4>
						&nbsp;&nbsp;&nbsp;您共送出了<%=rows%>次礼物&nbsp;&nbsp;&nbsp;&nbsp;
					</h4>
				</div>
			</div>

			<div class="row">
				<div class="col-md-12">



					<%
						Iterator<Productorder> iter = list.keySet().iterator();

						while (iter.hasNext()) {

							Productorder p = iter.next();
							List<SendInfo> slist = (List) list.get(p);
							out.println("<div class=\"panel panel-default\">");
							out.println("<h4>订单号：" + p.getProductOrderId() + " 时间："+p.getPurchaseTime()+"</h4>");
							out.println("<table>");
							out.println("<tr class=\"first\">");
							out.println("<td class=\"col-md-1  sortable\" rowspan=\"5\"><img align=\"top\"");
							out.println("src=\"" +basePath+p.getProductPic() + "\">");
							out.println("<br> "+p.getProductName()+"</td>");
							out.println("<td class=\"col-md-1 sortable\">赠送人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td class=\"col-md-1 sortable\">");
							/*
							if(p.getSendback() == 1){*/
								out.println("<a href=\""+basePath+"userPersonShow?uid="+p.getOrderTo()+"\">"+p.getToNickname()+"</a>");
							/*}
							else{
								out.println(p.getToNickname());
							/*}
							*/
							out.println("</td>");
							out.println("<td class=\"col-md-1 sortable\">总价值&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td class=\"col-md-1 sortable\">"
											+ p.getCountPrice() + "</td></tr>");
							out.println("<tr><td class=\"col-md-1 sortable\">赠送天数</td><td class=\"col-md-1 sortable\">"
									+ p.getSendDays() + "</td>");
							out.println("<td class=\"col-md-1 sortable\">每天赠送数</td><td class=\"col-md-1 sortable\">"
									+ p.getEverydayNumber() + "</td></tr>");
							out.println(
									"<tr><td class=\"col-md-1 sortable\">回赠</td><td class=\"col-md-1 sortable\" colspan=\"3\">");
							//派送
							if (p.getSendback() == 0) {//还没回赠
								out.print("对方未回赠</td>");
							} else if (p.getSendback() == 1) {
								//回赠的太阳花
								out.print("对方已回赠向阳花</td>");
							} else if (p.getSendback() == 2) {
								out.print("对方已回赠含羞草</td>");
							}

							out.println("</td></tr>");
							out.println(
									"<tr><td class=\"col-md-1 sortable\">派送信息</td><td colspan=\"3\"><ul>");
							//输出派送信息
							for (SendInfo si : slist) {
								out.print("<li>"+si.getSendTime() + "&nbsp;&nbsp;|&nbsp;&nbsp;" + si.getSendState()+
								"&nbsp;&nbsp;|&nbsp;&nbsp;留言:"+si.getMessage()+"</li>");
							}
							out.println("</ul></td></tr></table></div>");

						}
					%>
				</div>
			</div>

		</div>
	</div>
	<!-- end main container -->

</body>
</html>

