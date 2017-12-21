<%@ page language="java" import="java.util.*,config.*,dao.*,dao.impl.*,entity.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	HttpSession hs=request.getSession();
	String uid=(String)hs.getAttribute(Config.USER_ID);
	
	IUserDAO dao=new UserDAOImpl();
	String nickname=dao.findByID(uid).getNickname();
	
	
	
	//session.setAttribute(Config.USER_ID,uid);
%>
    <!-- navbar -->
    <header class="navbar navbar-inverse" role="banner">
        <div class="navbar-header">
            <button class="navbar-toggle" type="button" data-toggle="collapse" id="menu-toggler">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<%=basePath%>index.jsp">Flower</a>
        </div>
         <ul class="nav navbar-nav hidden-xs">
            
            <li >
                <a href="<%=basePath %>todayStar?s=0&m=1" class="hidden-xs hidden-sm">今日男神
                </a>
                
            </li>  
            <li >
                <a href="<%=basePath %>todayStar?s=0&m=0" class="hidden-xs hidden-sm">今日女神
                </a>
                
            </li>  
            <li >
                <a href="<%=basePath %>todayStar?s=1&m=1" class="hidden-xs hidden-sm">今日最壕男神
                </a>
                
            </li>  
            <li >
                <a href="<%=basePath %>todayStar?s=1&m=0" class="hidden-xs hidden-sm">今日最壕女神
                </a>
                
            </li>  
            
        </ul>
        <ul class="nav navbar-nav pull-right hidden-xs">
            
            <li >
                <a href="<%=basePath %>userPersonShow?uid=<%=uid %>" class="hidden-xs hidden-sm"><%=nickname %>
               
                </a>
            </li>  
            
            <li class="settings hidden-xs hidden-sm">
                <a href="<%=basePath %>logout" role="button">
                    <i class="icon-share-alt"></i>
                </a>
            </li>
        </ul>
    </header>
