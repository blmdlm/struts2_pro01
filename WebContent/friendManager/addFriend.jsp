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
<body bgcolor="gray">
	<hr noshade="noshade">
	<s:div align="center">
		<s:form action="findFriend.action" method="post">
			<table border="0" cellpadding="0" cellspacing="0" width="100%" align="center">
				<tr>
					<td width="33%"><s:text name="增加联系人"></s:text></td>
					<td width="33%"><s:a href="friendManager/lookFriends.jsp">查看联系人</s:a> </td>
					<td width="33%">
						<s:text name="修改联系人："></s:text>
						<input type="text" name="friendName"/>
						<input type="submit" value="查找"/> 
					</td>
				</tr>
			</table>
			
		</s:form>
	
	</s:div>
	
	<hr noshade="noshade">
	
	<form action="addFriend.action" method="post">
		<table border="2" cellpadding="0" cellspacing="0" bgcolor="95BDFF" width="60%" align="center">
			<tr>
				<td><s:textfield name="name" label="好友姓名"></s:textfield></td>
			</tr>
			
			<tr>
				<td><s:textfield name="phone" label="好友电话"></s:textfield></td>
			</tr>
			
			<tr>
				<td><s:textfield name="email" label="邮箱地址"></s:textfield></td>
			</tr>
			
			<tr>
				<td><s:textfield name="workplace" label="工作地址"></s:textfield></td>
			</tr>
			
			<tr>
				<td><s:textfield name="place" label="家庭地址"></s:textfield></td>
			</tr>
			
			<tr>
				<td><s:textfield name="QQ" label="QQ"></s:textfield></td>
			</tr>
			
			<tr>
				<td colspan="2" align="center" >
					<input type="submit" value="确定" size="12">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="reset" value="清除" size="12">
				
				</td>
			
			</tr>
		
		
		</table>
	
	
	</form>	
</body>
</html>