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
<body bgcolor="gray">
<hr noshade="noshade">
	<s:div align="center">
		<table border="0" cellpadding="0" cellspacing="0" width="100%" align="center">
		<tr>
				<td width="33%"><s:a href="personMessage/updateMessage.jsp">修改个人信息</s:a></td>
				<td width="33%"><s:a href="personMessage/lookMessage.jsp">查看个人信息</s:a></td>
				<td width="33%"><s:text name="修改个人密码"></s:text></td>
			</tr>
		</table>
	
	</s:div>
<hr noshade="noshade">
<s:form action="updatePass.action" method="post">
	<table border="5" cellpadding="0" cellspacing="0" bgcolor="#95BDFF" width="60%" align="center">
		<%
			ArrayList login=(ArrayList)session.getAttribute("userName");
			if(login==null||login.size()==0){
				response.sendRedirect("login/index.jsp");
			}else{
				for(int i=login.size()-1;i>=0;i--){
					UserBean user=(UserBean)login.get(i);
					%>
					<tr>
						<td height="30"><s:text name="用户密码"></s:text></td>
						<td><input type="text" name="password1" value="<%=user.getPassword()%>"> </td>
					</tr>
					
					<tr>
						<td height="30"><s:text name="重复密码"></s:text></td>
						<td><input type="text" name="password2" value="<%=user.getPassword()%>"> </td>
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