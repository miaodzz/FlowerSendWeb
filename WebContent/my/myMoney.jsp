<%@ page language="java" import="java.util.*,entity.*"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Money m = (Money) request.getAttribute("money");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>我的✿</title>
<jsp:include page="../static/must.jsp"></jsp:include>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>

<body>

	<jsp:include page="header.jsp" />
	<jsp:include page="left.jsp" />
<body>
	<%
		String info = (String) request.getAttribute("info");
		if (info != null && !info.equals("")) {
			out.print("<script type=\"text/javascript\">alert(\"" + info + "\");</script>");
		}
	%>
	<div class="content">
		<div id="pad-wrapper" class="user-profile">
			<h4
				style="color: #428bca; font-size: 30px; text-decoration: blink; text-align: justify;">✿✿My
				Flower✿✿</h4>
		</div>

					<div class="col-md-9 bio">
							<div class="row">
								<div class="col-md-6">
									<table class="table table-hover">
										<tbody>

											<tr class="first">
												<td>花芽数：<strong><%=m.getHuayaNum()%></strong>

												</td>

											</tr>
											<tr class="first">

												<td>花冠数：<strong><%=m.getHuaguanNum()%></strong>
												</td>
											</tr>

										</tbody>
									</table>
								</div>
							</div>

							<h4>
								<strong>✿我要兑换</strong>
							</h4>
							<p>1500花芽可兑换一个花冠，每一个花冠在提现时可提现5元。</p>
							<div class="row">
								<div class="col-md-8">
									<form action="<%=basePath%>my/huaya2huaguan" method="post">
										<table class="table table-hover">
											<tbody>
												<tr class="first">
													<td>兑换花芽数</td>

													<td><input
														onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
														onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
														name="ya2guan">
														*1500
														<input class="btn-flat small" type="submit" value="确定"></td>
												</tr>

											</tbody>
										</table>
									</form>
								</div>
							</div>
							<h4>
								<strong>✿我要充值</strong>
							</h4>
							<div class="row">
								<div class="col-md-8">
									<form action="<%=basePath %>my/huayaIn" method="post">
									<table class="table table-hover">
										
										<tbody>
										
											<tr class="first">
												<td>充值数量</td>
												<td><input
														onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
														onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
														name="ya">
														*10 
													<input
													class="btn-flat small" type="submit" value="确定"></td>
											</tr>
										
										</tbody>
									</table>
									</form>
								</div>
							</div>
							<h4>
								<strong>✿我要提现</strong>
							</h4>
							<div class="row">
								<div class="col-md-8">
								
								<form action="<%=basePath %>my/huaguanOut" method="post">
									<table class="table table-hover">
										<tbody>
											<tr class="first">
												<td>提现花冠</td>
												<td><input
														onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
														onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
														name="guan"><input
													class="btn-flat small" type="submit" value="确定"></td>
											</tr>
											<tr class="first">

											</tr>
										</tbody>
									</table>
								</form>
								</div>
							</div>
					
						</div>
					</div>

	<!-- scripts -->
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/theme.js"></script>
</body>
</html>

