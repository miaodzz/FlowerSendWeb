<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*,entity.*,config.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	User user = (User) request.getAttribute("user");
	String uid = (String) request.getSession().getAttribute(Config.USER_ID);
	List<Product> list = (List<Product>) request.getAttribute("productList");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="login-bg">
<head>
<title>赠送礼物</title>
<meta name="keywords" content="交友，婚恋，今日最佳，有趣，信息修改" />
<meta name="description" content="修改个人信息" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<jsp:include page="../static/must.jsp"></jsp:include>
<!-- 引入 jQuery -->


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
			<div class="col-md-9 bio">
				<div class="profile-box">
					<h4>赠送礼物</h4>
					<div class="row">
						<div class="col-md-12">
							<form method="post" action="<%=basePath%>my/sendGift">

								<div class="row">
									<table class="table table-hover">
										<tr class="first">
											<td>收礼者电话号码</td>
											<td><input
												onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
												onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
												type="text" class="col-md-3 form-control" id="orderto"
												name="ordertotel">
												<div id="tishi"></div></td>
										</tr>

										<tr class="first">
											<td>收礼地址</td>
											<td><input type="text" class="col-md-3 form-control"
												value="1" id="daynum" name="address">
												<div>如果不确定接收者是否注册,建议填写,若对方已经注册则以对方填写的地址为准。</div></td>
										</tr>
									</table>
									<table class="table table-hover">
										<thead>
											<tr>

												<th class="col-md-3">选择商品</th>
												<th class="col-md-3"><span class="line"></span>含义</th>
												<th class="col-md-3"><span class="line"></span>单价(花芽数)
												</th>
											</tr>
										</thead>
										<tbody>
											<!-- row -->
											<%
												for (Product p : list) {
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
											<tr class="first">
												<td>留言</td>
												<td><input type="text" class="col-md-3 form-control"
													value="1" id="message0" name="message0"></td>
											</tr>
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
	</div>
	<script>
		$(function() {

			
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
			
			
			$("form").submit(function(e) {

				if(/^1[0-9]{10}$/.test($("#orderto").val())) 
				{ 
					
					if($(':radio[name=pro]:checked').val()==null) {
					    alert('请选择礼物');
					    return false;
					}
					else{
						if($("#days").val()<1)
						{
							alert("数字不能小于1");
							return;
						}
						if($("#daynum").val()<1)
						{
							alert("数字不能小于1");
							return;
						}
						
						
						$("form").submit();
					}
				} else {
						alert("电话号码不符合规范!");
						return false;
				}
				
				
			
			});
			
			$("#orderto").bind('input').on('input propertychange', function() {
				if(/^1[0-9]{10}$/.test($("#orderto").val())) 
				{ 
					$("#tishi").html("输入电话号码符合规范");
				} else{
					$("#tishi").html("输入电话号码不符合规范");
				}
			});
		})
	</script>
</body>
</html>
