<%@ page language="java" import="java.util.*,entity.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<Productorder> list=(List<Productorder>)request.getAttribute("productOrderList");
int rows=(int)request.getAttribute("rows");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>订单列表</title>
    
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
  	<div class="content"">
    	<div class="admin">
  			<h1 class="title">我的订单</h1>
  		</div>
  	<div class="row">
                <div class="col-md-12">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th class="col-md-1 sortable">
                                    订单号
                                </th>
                                <th class="col-md-1 sortable">
                                    <span class="line"></span>
                                    赠送者
                                </th>
                                <th class="col-md-1 sortable">
                                    <span class="line"></span>
                                   赠予人
                                </th>
                                <th class="col-md-1 sortable">
                                    <span class="line"></span>
                                    商品
                                </th>
                                 <th class="col-md-1 sortable">
                                    <span class="line"></span>
                                    赠送天数
                                </th>
                                <th class="col-md-1 sortable">
                                    <span class="line"></span>
                                    每天赠送数量
                                </th>
                                <th class="col-md-1 sortable">
                                    <span class="line"></span>
                                    总价值
                                </th>
                                <th class="col-md-2 sortable">
                                    <span class="line"></span>
          	下单时间
                                </th>
                                 <th class="col-md-4 sortable">
                                    <span class="line"></span>
                                    查看派送
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                        
                        <% 
                        for(Productorder p:list)
                        {
                        	out.println("<tr class=\"first\">");
                        	out.println("<td>"+p.getProductOrderId()+"</td>");
                        	out.println("<td><a href=\""+basePath+"userPersonShow?uid="+p.getOrderBy()+"\" class=\"name\">"+ p.getByNickname()+"</a></td>");
                        	out.println("<td><a href=\""+basePath+"userPersonShow?uid="+p.getOrderTo()+"\" class=\"name\">"+ p.getToNickname()+"</a></td>");
                        	out.println("<td>" +p.getProductName()+"</td>");
                        	out.println("<td>"+p.getSendDays()+"</td>");
                        	out.println("<td>"+p.getEverydayNumber()+"</td>");
                        	out.println("<td>"+p.getCountPrice()+"</td>");
                        	out.println("<td>"+p.getPurchaseTime()+"</td>");
                        	out.print("<td> <a class=\"btn-flat small\" href=\""+basePath+"my/sendInfoList?kw="+p.getProductOrderId()+"\">派送情况</a></td>");
                        	out.println("</tr>");
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
