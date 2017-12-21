<%@ page language="java" import="java.util.*,entity.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
int rows = (Integer) request.getAttribute("rows");

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户花冠提现记录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="../static/must.jsp"/>
  </head>
  
  <body>
  
	<jsp:include page="header.jsp"/>
  	<jsp:include page="left.jsp"/>
  	<% 
	String info=(String)request.getAttribute("info");
	if(info!=null&&!info.equals(""))
	{
		out.print("<script type=\"text/javascript\">alert(\""+info+"\");</script>");
	}
	%>
  	<div class="content"">
    	<div class="admin">
  			<h1 class="title">用户花冠提现信息</h1>
  			<div class="welcome">每一颗花冠，都是女神的王冠~~</div>
  		</div>
  	<div class="row">
                <div class="col-md-12">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th class="col-md-2 sortable">
                                    处理号
                                </th>
                                <th class="col-md-2 sortable">
                                    <span class="line"></span>
                                   时间
                                </th>
                                <th class="col-md-2 sortable">
                                    <span class="line"></span>
                                    用户
                                </th>
                                <th class="col-md-2 sortable">
                                    <span class="line"></span>
                                    提现金额
                                </th>
                                 <th class="col-md-2 sortable">
                                    <span class="line"></span>
                                    花冠数
                                </th>
                                 <th class="col-md-2 sortable">
                                    <span class="line"></span>
                                   操作
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                
                      
                      
	 <%
		List<HuaguanCashOut> list = (List<HuaguanCashOut>) request.getAttribute("huaguanOutList");
		for (HuaguanCashOut hp : list) {

			out.print("<tr class=\"first\">");
			out.print("<td>" + hp.getSerialnumber() + "</td>");
			out.print("<td>" + hp.getTime() + "</td>");
			out.print("<td><a href=\""+basePath+"admin/userInfoUpdate?uid="+hp.getUserId()+"\" class=\"name\">"+ hp.getNickname()+"</a></td>");
			out.print("<td>" + hp.getMoney() + "</td>");
			out.print("<td>" + hp.getAmount() + "</td>");
			out.print("<td> <a class=\"btn-flat small\" href=\""+basePath+"admin/huaguanOutUpdate?sid="+hp.getSerialnumber()+"\">修改</a>");
			out.print(" <a class=\"btn-flat small\" href=\""+basePath+"admin/huaguanOutDelete?sid="+hp.getSerialnumber()+"\">删除</a></td>");
			out.print("</tr>");
				
		}
	%>
                        </tbody>
                    </table>
                </div>                
            </div>	
  	
  	
    </div>
  
  	<jsp:include page="footer.jsp"></jsp:include>
  </body>
</html>
