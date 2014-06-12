<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
            <%@taglib prefix="s" uri="/struts-tags" %>
            <%@page import="com.blmdlm.JavaBean.MyFriBean"%>
<%@page import="java.util.ArrayList"%>
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
	
	<s:div align="ceter">
		<s:form action="findFriend.action" method="post">
			<table border="0" cellpadding="0" cellspacing="0" width="100%" align="center">
				<tr>
					<td width="33%"><s:a href="friendManager/addFriend.jsp">增加联系人</s:a></td>
					<td width="33%"><s:a href="friendManager/lookFriends.jsp">查看联系人</s:a></td>
					<td width="33%">
						<s:text name="修改联系人："></s:text>
						<input type="text" name="friendName"/>
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
			<th height="30">用户操作</th>
		</tr>
		
		<%
			ArrayList friend=(ArrayList)session.getAttribute("findFriend");
		//其实已经验证过了
			if(friend==null||friend.size()==0){
				%>
					<s:div align="center"><%="您还没有添加联系人！" %></s:div>
				<% 
			}else{
				for(int i=friend.size()-1;i>=0;i--){
					MyFriBean ff=(MyFriBean)friend.get(i);
					%>
					<tr>
						<td ><%=ff.getName() %></td>
						<td ><%=ff.getPhone() %></td>
						<td ><%=ff.getEmail() %></td>
						<td ><%=ff.getWorkplace() %></td>
						<td ><%=ff.getPlace() %></td>
						<td ><%=ff.getQQ() %></td>
						<td>
							<s:a href="friendManager/updateFriend.jsp">修改</s:a>
							<s:a href="deleteFriend.action">删除</s:a>
						</td>
						
						
					</tr>
					
					
					<% 
				}
			}
				
		
		
		%>
		
	
	</table>
	
	
	
	
</body>
</html>