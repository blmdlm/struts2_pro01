<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<title><s:text name="个人信息管理系统"></s:text></title>
</head>
<body bgcolor="#CCCCFF">
	<table style="width: 300px">
		<tr>
			<td><img alt="标志" src="images/left01.jpg" ></td>
		</tr>
			<tr>
			<td><img alt="girl" src="images/girl02.jpg" ></td>
		</tr>
	</table>
	
</body>
</html>