<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
%>
<!-- sidebar -->
<div id="sidebar-nav">
	<ul id="dashboard-menu">
		<li class="active">
			<div class="pointer">
				<div class="arrow"></div>
				<div class="arrow_border"></div>
			</div> <a href="<%=basePath%>admin/index.jsp"> <i class="icon-home"></i> <span>首页</span>
		</a>
		</li>
		<li><a class="dropdown-toggle" href="#"> <i
				class="icon-group"></i> <span>用户管理</span> <i class="icon-chevron-down"></i>
		</a>
			<ul class="submenu">
				<li><a href="<%=basePath%>admin/userList?p=1">用户列表</a></li>
				<li><a href="<%=basePath%>admin/managerAdd.jsp">管理员增加</a></li>
			</ul></li>
		<li><a class="dropdown-toggle" href="#"> <i
				class="icon-gift"></i> <span>商品管理</span> <i class="icon-chevron-down"></i>
		</a>
			<ul class="submenu">
				<li><a href="<%=basePath%>admin/productList">已有商品</a></li>
				<li><a href="<%=basePath%>admin/productAdd">增加商品</a></li>
			</ul></li>
		<li><a class="dropdown-toggle" href="#"> <i class="icon-edit"></i>
				<span>订单管理</span> <i class="icon-chevron-down"></i>
		</a>
			<ul class="submenu">
				<li><a href="<%=basePath%>admin/productorderList">订单管理</a></li>
				<li><a href="<%=basePath%>admin/sendInfoList">派送信息</a></li>
			</ul></li>
		<li><a class="dropdown-toggle" href="<%=basePath%>admin/huayaInList"> <i
				class="icon-th-large"></i> <span>充值管理</span> <i
				class="icon-chevron-down"></i>
		</a>
			<ul class="submenu">
				<li><a href="<%=basePath%>admin/huayaInList">用户充值记录</a></li>
			</ul></li>
		<li><a class="dropdown-toggle ui-elements" href="<%=basePath%>admin/huaguanOutList"> <i
				class="icon-money"></i> <span>提现管理</span> <i
				class="icon-chevron-down"></i>
		</a>
			<ul class="submenu">
				<li><a href="<%=basePath%>admin/huaguanOutList">用户提现记录</a></li>
			</ul></li>
		<li><a class="dropdown-toggle" href="#"> <i
				class="icon-share-alt"></i> <span>其它</span> <i
				class="icon-chevron-down"></i>
		</a>
			<ul class="submenu">
				<li><a href="<%=basePath%>index.jsp">前台首页</a></li>
				<li><a href="<%=basePath%>logout">注销登录</a></li>
			</ul></li>
	</ul>
</div>


