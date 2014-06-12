<%@page import="com.blmdlm.JavaBean.UserBean"%>
<%@page import="java.util.ArrayList"%>
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
<body bgcolor="CCDDEE">
	<%
		String loginName=null;
		ArrayList login=(ArrayList)session.getAttribute("userName");
		if(login==null||login.size()==0){
			loginName="瓜大";
		}else{
			for(int i=login.size()-1;i>=0;i--){
				UserBean user=(UserBean)login.get(i);
				loginName=user.getUserName();
			}
		}
	%>

	<table width="100%" align="right" bgcolor="blue">
		<tr height="10" bgcolor="gray" align="center">
			<td><a href="personMessage/lookMessage.jsp"  target="main">个人信息管理</a></td>
			<td><a href="friendManager/lookFriends.jsp"  target="main">通讯录管理</a></td>
			<td><a href="dateTimeManager/lookDay.jsp"  target="main">日程安排管理</a></td>
			<td><a href="fileManager/lookFile.jsp"  target="main">个人文件管理</a></td>
			<td><a href="login/index.jsp"  target="_top">退出主页面</a></td>
			<td>欢迎<%=loginName %>使用本系统！</td>
		</tr>
	</table>
	
</body>
</html>