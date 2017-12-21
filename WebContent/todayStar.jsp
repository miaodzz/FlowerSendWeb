<%@ page language="java" import="java.util.*,entity.*,config.*"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	User user = (User) request.getAttribute("user");
	List<UserPicture> list = (List) request.getAttribute("pictureList");
	List<Product> listp = (List) request.getAttribute("productList");
	String tel = (String) request.getAttribute("tel");
	String sex = (String) request.getAttribute("sex");
	String send = (String) request.getAttribute("send");
%>


<html class="login-bg">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<head>
<title>todayStar</title>
<meta name="keywords" content="交友，婚恋，今日最佳，有趣，个人展示" />
<meta name="description" content="个人展示板" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<jsp:include page="./static/must.jsp"></jsp:include>

<!-- this page specific styles -->
<link rel="stylesheet"
	href="<%=basePath%>static/css/compiled/signin.css" type="text/css"
	media="screen" />

<!-- open sans font -->
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,700italic,800italic,400,300,600,700,800'
	rel='stylesheet' type='text/css'>

<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<link rel="stylesheet"
	href="<%=basePath%>static/css/compiled/user-profile.css"
	type="text/css" media="screen" />

</head>
<body>
	<jsp:include page="./my/header.jsp"></jsp:include>
	<jsp:include page="./my/left.jsp"></jsp:include>
	<div class="content">


		<div id="pad-wrapper" class="user-profile">
			<!-- header -->
			<div class="row header">
				<div class="col-md-8">
					<img src="<%=basePath + user.getAvatar()%>"
						class="avatar img-circle">
					<h3 class="name">
						今日<%=sex%>神:<%=send%></h3>
					<span class="area"><%=user.getSignature()%></span>
				</div>
			</div>

			<div class="row profile">
				<!-- bio, new note & orders column -->
				<div class="col-md-9 bio">
					<div class="profile-box">
						<!-- biography -->

						<%
							if (list.size() != 0) {
								out.print("<h4>照片墙</h4>");

								for (UserPicture p : list) {
									out.print("<div class=\"img\"><img src=\"" + basePath + p.getPicture()
											+ "\" width=\"400\"></div><p>");
								}
							}
						%>

						<h4>基本信息</h4>
						<div class="row">
							<div class="col-md-12">
								<table class="table table-hover">
									<tbody>

										<tr class="first">
											<td>姓名</td>
											<td><%=user.getTruename()%></td>
										</tr>
										<tr class="first">
											<td>生日</td>
											<td><%=user.getBirthday()%></td>
										</tr>
										<tr class="first">
											<td>电话</td>
											<td><%=user.getTelephone()%></td>
										</tr>
										<tr class="first">
											<td>住址</td>
											<td><%=user.getAddress()%></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<h4>最近购买信息</h4>

					<%
						Map list1 = (Map) request.getAttribute("productordermap1");
						Iterator<Productorder> iter = list1.keySet().iterator();

						while (iter.hasNext()) {

							Productorder p = iter.next();
							List<SendInfo> slist = (List) list1.get(p);
							out.println("<div class=\"panel panel-default\">");
							out.println("<table>");
							out.println("<tr class=\"first\">");
							out.println("<td class=\"col-md-1  sortable\" rowspan=\"6\"><img align=\"top\"");
							out.println("src=\"" + basePath + p.getProductPic() + "\">");
							out.println("<br> " + p.getProductName() + "</td>");
							out.println(
									"<td class=\"col-md-1 sortable\">赠送人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td colspan=\"3\" class=\"col-md-1 sortable\">");
							out.println(p.getToNickname());
							out.println("</td></tr>");
							out.println(
									"<tr><td class=\"col-md-1 sortable\">总价值&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td class=\"col-md-1 sortable\">"
											+ p.getCountPrice() + "</td></tr>");
							out.println("<tr><td class=\"col-md-1 sortable\">赠送天数</td><td class=\"col-md-1 sortable\">"
									+ p.getSendDays() + "</td></tr>");
							out.println("<tr><td class=\"col-md-1 sortable\">每天赠送数</td><td class=\"col-md-1 sortable\">"
									+ p.getEverydayNumber() + "</td></tr>");
							out.println("<tr><td class=\"col-md-1 sortable\">时间</td><td class=\"col-md-1 sortable\" colspan=\"3\">"
									+ p.getPurchaseTime() + "</td></tr>");
							out.println("</table></div>");

						}
					%>



					<h4>收到礼物</h4>
					<%
						Map list2 = (Map) request.getAttribute("productordermap2");
						Iterator<Productorder> iter2 = list2.keySet().iterator();

						while (iter2.hasNext()) {

							Productorder p = iter2.next();
							List<SendInfo> slist = (List) list2.get(p);
							out.println("<div class=\"panel panel-default\">");
							out.println("<table>");
							out.println("<tr class=\"first\">");
							out.println("<td class=\"col-md-1  sortable\" rowspan=\"6\"><img align=\"top\"");
							out.println("src=\"" + basePath + p.getProductPic() + "\">");
							out.println("<br> " + p.getProductName() + "</td>");
							out.println(
									"<td class=\"col-md-1 sortable\">赠送人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td colspan=\"3\" class=\"col-md-1 sortable\">");
							out.println(p.getByNickname());
							out.println("</td></tr>");
							out.println(
									"<tr><td class=\"col-md-1 sortable\">总价值&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td class=\"col-md-1 sortable\">"
											+ p.getCountPrice() + "</td></tr>");
							out.println("<tr><td class=\"col-md-1 sortable\">赠送天数</td><td class=\"col-md-1 sortable\">"
									+ p.getSendDays() + "</td></tr>");
							out.println("<tr><td class=\"col-md-1 sortable\">每天赠送数</td><td class=\"col-md-1 sortable\">"
									+ p.getEverydayNumber() + "</td></tr>");

							out.println("<tr><td class=\"col-md-1 sortable\">时间</td><td class=\"col-md-1 sortable\" colspan=\"3\">"
									+ p.getPurchaseTime() + "</td></tr>");
							out.println("</table></div>");

						}
					%>

					<!-- new comment form -->
					<div class="col-md-12 section comment"
						<%if (user.getUserId().equals((String) request.getSession().getAttribute(Config.USER_ID))) {
				out.print("style=\"display:none;\"");
			}%>>
						<h4>给TA送礼</h4>
						<p>为你们的互动留下你的足迹</p>
						<form method="post" action="<%=basePath%>my/sendGift">
							
							<input type="hidden" id="orderto"
												name="ordertotel" value="<%=user.getUserId()%>">
							<div class="row">
								<table class="table table-hover">
									<thead>
										<tr>

											<th class="col-md-3">选择商品</th>
											<th class="col-md-3"><span class="line"></span>含义</th>
											<th class="col-md-3"><span class="line"></span>单价(花芽数)</th>
										</tr>
									</thead>
									<tbody>
										<!-- row -->
										<%
											for (Product p : listp) {
												out.print("<tr class=\"first\">");
												out.print("<td><input type=\"radio\" name=\"pro\" value=\"" + p.getProductId() + "\"> ");
												out.print("<span align=\"center\" class=\"img\"><img   src=\"" + basePath + p.getProductPicture()
														+ "\"><div align=\"center\">" + p.getProductName() + "</div></span></td>");
												out.print("<td class=\"description\">" + p.getProductMean() + "</td>");
												out.print("<td class=\"description\">" + p.getProductPrice() + "</td>");
												out.print("</tr>");
											}
										%>
									</tbody>
								</table>
								<table class="table table-hover">


									<tbody>

										<tr class="first">
											<td>连续赠送天数</td>
											<td><input type="text"
												onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
												onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
												class="col-md-3 form-control" value="1" id="days"
												name="days"></td>
										</tr>

										<tr class="first">
											<td>每天赠送数量</td>
											<td><input type="text"
												onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
												onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
												class="col-md-3 form-control" value="1" id="daynum"
												name="daynum"></td>
										</tr>




									</tbody>
									<tbody id="toadd">

											<tr class="first"><td>留言</td><td><input type="text" class="col-md-3 form-control" value="1" id="message0" name="message0"></td></tr>
									
									</tbody>
									<tr>
										<td></td>
										<td><button class="btn-flat small" type="submit"
												value="Submit">确定</button></td>
									</tr>
								</table>

							</div>




						</form>

					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var inp1 = '<tr class="first"><td>留言';
		var inp2 = '</td><td><input type="text" class="col-md-3 form-control" value="1" id="message" name="message';
		var inp3='"></td></tr>';
		$("#days").bind('input').on('input propertychange', function() {
			var inp = '';
			var n = $("#days").val();
			for (var i = 0; i < n; i++) {
				inp = inp + inp1 + "第" + (i + 1) + "天" + inp2 + i + inp3;
			}
			$("#toadd").html(inp);
		});
	</script>
</body>
</html>