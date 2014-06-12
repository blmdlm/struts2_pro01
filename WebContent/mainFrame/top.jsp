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
	<table width="100%" align="center">
		<tr align="center">
			<td align="right"><img alt="头像" src="images/touxiang.jpg" height="80"></td>
			<td align="left"><h1><font color="blue">欢迎使用个人信息管理平台</font></h1></td>
		</tr>
	</table>
	
</body>
</html>