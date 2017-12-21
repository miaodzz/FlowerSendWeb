<%@ page language="java" import="java.util.*,entity.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	List<Product> list=(List)request.getAttribute("productList");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="login-bg">
<head>
	<title>商品列表</title>
	<meta name="keywords" content="交友，婚恋，今日最佳，有趣，商品列表" />
	<meta name="description" content="用户列表" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<jsp:include page="../static/must.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="header.jsp"/>
  	<jsp:include page="left.jsp"/>
	<div class="content">
		<div id="pad-wrapper" class="users-list">
			<div class="row header">
				<h4>商品列表</h4>
				<div class="col-md-10 col-sm-12 col-xs-12 pull-right">
					<a href="<%=basePath%>admin/productAdd" class="btn-flat success pull-right"> <span>&#43;</span>
						添加商品
					</a>
				</div>
			</div>

			<div class="row">
				<div class="col-md-12">
					<table class="table table-hover">
												<thead>
													<tr>

														<th class="col-md-3">商品</th>
														<th class="col-md-3"><span class="line"></span>含义</th>
														<th class="col-md-1"><span class="line"></span>单价(花芽数)
														</th>
														<th class="col-md-2"><span class="line"></span>操作
														</th>
													</tr>
												</thead>
												<tbody>
													<!-- row -->
													<%
														for (Product p : list) {
															out.print("<tr class=\"first\">");
															out.print("<td>");
															out.print("<span class=\"img\"><img src=\"" + basePath+p.getProductPicture() + "\"></span>" + p.getProductName()
																	+ "</td>");
															out.print("<td class=\"description\">" + p.getProductMean() + "</td>");
															out.print("<td class=\"description\">" + p.getProductPrice() + "</td>");
															out.print("<td> <a class=\"btn-flat small\" href=\""+basePath+"admin/productUpdate?pid="+p.getProductId()+"\">修改</a>");
															if(p.getProductId()>2)
																out.print(" <a class=\"btn-flat small\" href=\""+basePath+"admin/productDelete?pid="+p.getProductId()+"\">删除</a>");
															
															out.print("</td></tr>");
														}
													%>                     
												</tbody>
											</table>
					
				</div>
			</div>
			
			
		</div>
	</div>
	<!-- end main container -->

</body>
</html>
