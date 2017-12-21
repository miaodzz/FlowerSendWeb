<%@page import="entity.*,config.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String uid=(String)request.getSession().getAttribute(Config.USER_ID);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>我的美照</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<jsp:include page="../static/must.jsp" />
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
			<h4>我的美照</h4>
			<!-- header -->
			<div class="col-md-9 bio">
				<div class="profile-box">
				
				<table class="table table-hover">
					<thead>
						<tr>
							<th class="col-md-2 sortable">照片</th>

						</tr>
					</thead>
					<tbody>
						<%
							List<UserPicture> list = (List) request.getAttribute("pictureList");
							for (UserPicture p : list) {
								out.print("<tr class=\"first\"><td><div class=\"img\"><img src=\"" + basePath + p.getPicture()
										+ "\" width=\"400\"></div><div>"
										+"<a class=\"icon-remove-sign\" href=\""+basePath+"my/userPictureDelete?pic="+p.getPicture()+"\"></a>"
										+"</div><p></td></tr>");
							}
						%>

					</tbody>
				</table>
			</div>
			
			<form name="form1" method="post"
						action="<%=basePath%>my/uploadUserPicture" id="form1"
						enctype="multipart/form-data">
						<input type="hidden" name="uid" value="<%=uid%>"></input>
						<div class="right">
							<!--Step 1-->
							<div id="Step1Container">
								<div class="title">
									<b>上传照片</b>
								</div>
								<div id="uploadcontainer">
									<div class="uploadtooltip">请选择新的照片文件，文件需小于2.5MB</div>
									<div class="uploaddiv">
										<input type="file" name="fuPhoto" id="fuPhoto" title="选择照片" />
									</div>
									<div class="uploaddiv">
										<input class="btn-flat" type="submit" name="btnUpload" value="上 传"
											id="btnUpload" />
									</div>
								</div>

							</div>
						</div>
					</form>
		</div>
		</div>

	</div>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>