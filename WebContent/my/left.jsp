<%@ page language="java" import="java.util.*,config.*" pageEncoding="UTF-8"%>
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
			</div> <a href="<%=basePath%>userPersonShow?uid=<%=request.getSession().getAttribute(Config.USER_ID) %>"> <i class="icon-home"></i> <span>个人中心</span>
		</a>
		</li>
		
		<li>
			<a href="<%=basePath%>my/sendGift"> <i class="icon-gift"></i> <span>我要送礼</span>
		</a>
		</li>
		
		<li><a class="dropdown-toggle" href="#"> <i
				class="icon-user"></i> <span>我的资料</span> <i class="icon-chevron-down"></i>
		</a>
			<ul class="submenu">
				<li><a href="<%=basePath%>my/userInfoUpdate">资料修改</a></li>
				<li><a href="<%=basePath%>my/userPasswdUpdate.jsp">密码修改</a></li>
				<li><a href="<%=basePath%>my/userAvatarUpdate">头像修改</a></li>
				<li><a href="<%=basePath%>my/gallery">我的美照</a></li>
			</ul>
		</li>
		
		
		
		<li><a class="dropdown-toggle" href="#"> <i class="icon-group"></i>
				<span>礼尚往来</span> <i class="icon-chevron-down"></i>
		</a>
			<ul class="submenu">
				<li><a href="<%=basePath%>my/orderByMe">送礼记录</a></li>
				<li><a href="<%=basePath%>my/orderToMe">收礼记录</a></li>
			</ul>
		</li>
		
		<li><a class="dropdown-toggle" href="<%=basePath%>my/mymoney"> <i
				class="icon-money"></i> <span>充值提现</span> <i
				class="icon-chevron-down"></i>
		</a>
			<ul class="submenu">
				<li><a href="<%=basePath%>my/mymoney">充值提现</a></li>
			</ul>
			<ul class="submenu">
				<li><a href="<%=basePath%>my/huayaInList">充值记录</a></li>
			</ul>
			<ul class="submenu">
				<li><a href="<%=basePath%>my/huaguanOutList">提现记录</a></li>
			</ul>
		</li>
		
		
		<li><a class="dropdown-toggle" href="#"> <i
				class="icon-wrench"></i> <span>其它</span> <i
				class="icon-chevron-down"></i>
		</a>
			<ul class="submenu">
				<li><a href="<%=basePath%>index.jsp">网站首页</a></li>
				<li><a href="<%=basePath%>logout">注销登录</a></li>
			</ul></li>
	</ul>
</div>


