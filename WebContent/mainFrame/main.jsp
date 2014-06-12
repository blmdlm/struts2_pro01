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

<frameset cols="20%,*" framespacing="0" border="no" frameborder="0">
	<frame src="mainFrame/left.jsp" name="left" scrolling="no">
	
	<frameset rows="20%,10%,*">
		<frame src="mainFrame/top.jsp" name="top" scrolling="no">
		<frame src="mainFrame/toop.jsp" name="toop" scrolling="no">
		<frame src="mainFrame/about.jsp" name="main">  <!--  ???-->
	</frameset>

</frameset>


</html>