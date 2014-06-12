<%@page import="com.blmdlm.JavaBean.MyFriBean"%>
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
<body bgcolor="gray">
	<hr noshade="noshade">
	<s:div align="center">
		<s:form action="findFriend.action" method="post">
			<table border="0" cellpadding="0" cellspacing="0" width="100%" align="center">
				<tr>
					<td width="33%"><s:a href="friendManager/addFriend.jsp">增加联系人</s:a></td>
					<td width="33%"><s:text name="查看联系人"></s:text> </td>
					<td width="33%">
						<s:text name="修改联系人:"></s:text>
						<input type="text" name="friendName">
						<input type="submit" value="查找">
					</td>
				
				</tr>
			</table>
		
		</s:form>
	
	</s:div>
	
	<hr noshade="noshade">
	<table border="5" cellpadding="0" cellspacing="0" bgcolor="#95BDFF" width="60%" align="center">
		<tr>
			<th height="30">好友姓名</th>
			<th height="30">好友电话</th>
			<th height="30">邮箱地址</th>
			<th height="30">工作单位</th>
			<th height="30">家庭住址</th>
			<th height="30">QQ</th>
		</tr>
		
		<%
			ArrayList friends=(ArrayList)session.getAttribute("friends");
			if(friends==null||friends.size()==0){
				%>
					<s:div align="center"><%="您还没有添加联系人！" %></s:div>
				<% 
			}else{
				for(int i=friends.size()-1;i>=0;i--){
					MyFriBean friend=(MyFriBean)friends.get(i);
					%>
					<tr>
						<td ><%=friend.getName() %></td>
						<td ><%=friend.getPhone() %></td>
						<td ><%=friend.getEmail() %></td>
						<td ><%=friend.getWorkplace() %></td>
						<td ><%=friend.getPlace() %></td>
						<td ><%=friend.getQQ() %></td>
					</tr>
					
					
					<% 
				}
			}
				
		
		
		%>
		
		
		
		
	
	</table>
	
	
</body>
</html>