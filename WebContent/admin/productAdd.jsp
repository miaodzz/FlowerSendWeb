<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*,entity.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Product p=(Product)request.getAttribute("product");
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="login-bg">
<head>
<title>管理员：商品信息页</title>
<meta name="keywords" content="交友，婚恋，今日最佳，有趣，信息修改" />
<meta name="description" content="修改个人信息" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<jsp:include page="../static/must.jsp"></jsp:include>
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
				<h4>商品信息</h4>
				<div class="row">
					<div class="col-md-12">
						<form method="post" action="<%=basePath%>admin/productAdd">
							<table class="table table-hover">
								<tbody>
									
									<tr class="first">
										<td>商品名称</td>
										<td><input type="text" class="col-md-9 form-control"
											id="name" name="name" value="<%=p.getProductName() %>" />

										</td>
									</tr>
									<tr class="first">
										<td>商品含义</td>
										<td><input type="text" class="col-md-9 form-control"
											id="mean" name="mean" value="<%=p.getProductMean()%>"/></td>
									</tr>
									<tr class="first">
										<td>商品类型</td>
										<td><select class="ui-select" id="type" name="type">
												<option value="<%=p.getProductType() %>"><%=p.getProductType()%></option>
												<option value="<%=p.getProductTypeOp() %>"><%=p.getProductTypeOp()%></option>

										</select>
										</td>
									</tr>
									<tr class="first">
										<td>单个价格</td>
										<td>
										<input class="form-control" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
											onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
											name="price" value="<%=p.getProductPrice()%>">										
										</td>
										<td>元（必须为正整数）</td>


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

	<script src="<%=basePath%>js/bootstrap-datepicker.js"></script>
	<script>
		$(document).ready(function() {
			$('#selectDate').datepicker();
		});
	</script>

</body>
</html>