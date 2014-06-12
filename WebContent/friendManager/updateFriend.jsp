<%@page import="com.blmdlm.JavaBean.MyFriBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.ArrayList"%>
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
	
	<s:form action="updateFriend.action" method="post">
		<table border="2" cellpadding="0" cellspacing="0" bgcolor="#95BDFF" width="60%" align="center">
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
							<td><s:text name="用户姓名"></s:text></td>
							<td><input type="text" name="name" value="<%=ff.getName() %>"/></td>
						</tr>
						
						<tr>
							<td><s:text name="用户电话"></s:text></td>
							<td><input type="text" name="phone" value="<%=ff.getPhone()%>"/></td>
						</tr>
						
						<tr>
							<td><s:text name="邮箱地址"></s:text></td>
							<td><input type="text" name="email" value="<%=ff.getEmail() %>"/></td>
						</tr>
						
						<tr>
							<td><s:text name="工作地址"></s:text></td>
							<td><input type="text" name="workplace" value="<%=ff.getWorkplace() %>"/></td>
						</tr>
						
						<tr>
							<td><s:text name="家庭住址"></s:text></td>
							<td><input type="text" name="place" value="<%=ff.getPlace() %>"/></td>
						</tr>
						
						<tr>
							<td><s:text name="QQ"></s:text></td>
							<td><input type="text" name="QQ" value="<%=ff.getQQ() %>"/></td>
						</tr>
						
						<tr>
							<td colspan="2" align="center">
								<input type="submit" value="确定" size="12"/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="reset" value="清除" size="12"/>
							</td>
						
						
						</tr>
						
						
						<%
					}
				}
			%>
		
		</table>
	
	
	</s:form>
	
	
	
	
	
</body>
</html>