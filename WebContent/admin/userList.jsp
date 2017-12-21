<%@ page language="java" import="java.util.*,entity.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	List<User> list=(List)request.getAttribute("userList");
	int p=(Integer)request.getAttribute("p");
	int countAll=(Integer)request.getAttribute("rows");
	int pageAll=(countAll+9)/10;
	String kw=(String)request.getAttribute("kw");
	if(kw==null) kw="";
	String r=(String)request.getAttribute("r");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="login-bg">
<head>
	<title>用户列表</title>
	<meta name="keywords" content="交友，婚恋，今日最佳，有趣，用户列表" />
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
				<h4>用户列表</h4>
				<div class="col-md-10 col-sm-12 col-xs-12 pull-right">
					
					<!-- custom popup filter -->
					<!-- styles are located in css/elements.css -->
					<!-- script that enables this dropdown is located in js/theme.js -->
					<div class="ui-dropdown">
						
						<div class="head" data-toggle="tooltip" title="点我筛选用户">
							Filter users <i class="arrow-down"></i>
						</div>
						<div class="dialog">
							<div class="pointer">
								<div class="arrow"></div>
								<div class="arrow_border"></div>
							</div>
							<div class="body"> 
								<p class="title">选择查询条件</p>
								<div class="form">
									<form action="userList" method="get">
										<select class="ui-select" id="r" name="r">
											<option value="all">全部类别</option>
											<option value="user">普通用户</option>
											<option  value="unregistered">未注册</option>
											<option  value="manager">管理员</option>
										</select>  
                                		<input class="search" size="20" type="text" id="kw" name="kw" />
                              
										<input type="submit" class="btn-flat small"/>
									</form>
								</div>
								
							</div>
						</div>
					</div>
					
					<h4>&nbsp;&nbsp;&nbsp;符合条件的用户有<%=countAll%>位&nbsp;&nbsp;&nbsp;&nbsp;</h4>
					<a href="<%=basePath%>>admin/managerAdd" class="btn-flat success pull-right"> <span>&#43;</span>
						添加管理员
					</a>
				</div>
			</div>

			<div class="row">
				<div class="col-md-12">
					<table class="table table-hover">
						<thead>
							<tr>
								<th class="col-md-2 sortable">头像</th>
								<th class="col-md-2 sortable"><span class="line"></span> 昵称
								</th>
								<th class="col-md-2 sortable"><span class="line"></span> 姓名
								</th>
								<th class="col-md-4 sortable"><span class="line"></span>
									电话号码</th>
								<th class="col-md-2 sortable"><span class="line"></span>
									等级</th>	
								<th class="col-md-2 sortable"><span class="line"></span>
									删除</th>	
							</tr>
						</thead>
						<tbody>
							<% 
							for(User user:list){
								out.println("<tr class=\"first\">");
								out.println("<td><a href=\""+basePath+"admin/userAvatarUpdate?uid="+user.getUserId()+"\" class=\"name\"> <img ");
								out.println("src=\""+basePath+user.getAvatar()+"\"");
								out.println("class=\"img-circle avatar hidden-phone\" />");
								out.println("</a></td>");
								out.println("<td><a href=\""+basePath+"admin/userInfoUpdate?uid="+user.getUserId()+"\" class=\"name\">"+ user.getNickname()+"</a></td>");
								out.println("<td>"+user.getTruename()+"</td>");
								out.println("<td>"+user.getTelephone()+"</td>");
								out.println("<td>"+user.getRole()+"</td>");
								out.println("<td><a href=\""+basePath+"admin/userDelete?uid="+user.getUserId()+"\" class=\"icon-remove-sign\"></a></td>");
								
								out.println("</tr>");
							}
							%>
								

						</tbody>
					</table>
				</div>
			</div>
			
			<ul class="pagination pull-right">
			<!-- 分页还没家 -->
			<li><a href="
			<%
				int piece=(p-1)/5;//0,1,2,3,...
				int pos=(p-1)%5;  //0,1,2,3,4,5,~9
				
				if(p==1)
				{
					out.print("#");
				}
				else {out.print("userList?r="+r+"&kw="+kw+"&p=");out.print(piece*5+1);}
				int pagestart=piece*5+pos+1;
			%>
				">&#8249;</a></li>
				<li <%if((pagestart)==p)out.print("class=\"active\"");%>><a href="userList?r=<%=r %>kw=<%=kw%>&p=<%=pagestart%>">
				<%=pagestart%></a></li>
				<li <%if((pagestart+1)==p)out.print("class=\"active\"");%>><a href="userList?r=<%=r %>&kw=<%=kw%>&p=<% if(pagestart+1<=pageAll)out.print(pagestart+1); else out.print(p);%>">
				<%=pagestart+1%></a></li>
				<li <%if((pagestart+2)==p)out.print("class=\"active\"");%>><a href="userList?r=<%=r %>&kw=<%=kw%>&p=<% if(pagestart+2<=pageAll)out.print(pagestart+2); else out.print(p);%>">
				<%=pagestart+2%></a></li>
				<li <%if((pagestart+3)==p)out.print("class=\"active\"");%>><a href="userList?r=<%=r %>&kw=<%=kw%>&p=<% if(pagestart+3<=pageAll)out.print(pagestart+3); else out.print(p);%>">
				<%=pagestart+3%></a></li>
				<li <%if((pagestart+4)==p)out.print("class=\"active\"");%>><a href="userList?r=<%=r %>&kw=<%=kw%>&p=<% if(pagestart+4<=pageAll)out.print(pagestart+4); else out.print(p);%>">
				<%=pagestart+4%></a></li>
				
				<li><a href="<%
					if(piece*5+5>=pageAll){
						out.print("#");
					}else{
						out.print("userList?kw="+kw+"&p=");out.print(piece*5+6);
						} %>">&#8250;</a></li>
			</ul> 
			<!-- end users table -->
		</div>
	</div>
	<!-- end main container -->

</body>
</html>

