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
<title><s:text name="个人信息管理系统->注册"/></title>
</head>
<body bgcolor="#CCCCFF">
	<s:form action="register.action" method="post">
		<table align="center">
			<tr>   
				<td width="40%">                      
					<table border="2" bgcolor="#AABBCCDD" width="100%" align="center">
						<tr>
							<td colspan="2" align="center">
								<font color="yellow">
									<s:text name="请填写以下注册信息"></s:text>
								</font>
							</td>
						</tr>
						
						<tr>
							<td>
								<s:textfield name="userName" label="登录名"></s:textfield>
							</td>
						</tr>
						
						<tr>
							<td>
								<s:password name="password1" label="密码" size="21"></s:password>
							</td>
						</tr>
						
						<tr>
							<td>
								<s:password name="password2" label="请再次输入密码" size="21"></s:password>
							</td>
						</tr>
						
						<tr>
							<td>
								<s:textfield name="name" label="真实姓名"></s:textfield>
							</td>
						</tr>
						
						<tr>
							<td>
								<s:text name="性别" ></s:text>
							</td>
							<td>
								<input type="radio" name="sex" value="男" checked="checked"/>男
								<input type="radio" name="sex" value="女" />女
							</td>
						</tr>
						
						<tr>
							<td>
								<s:textfield name="birth" label="出生日期"></s:textfield>
							</td>
						</tr>
						
						<tr>
							<td>
								<s:textfield name="nation" label="民族"></s:textfield>
							</td>
						</tr>
						
						<tr>
							<td>
								<s:select name="edu" label="学历"  headerValue="-----请选择----" headerKey="1" list="{'博士','硕士','本科','专科','高中','初中','其他'}"></s:select>
							</td>
						</tr>
						
						
						<tr>
							<td>
								<s:select name="work" label="职称"  headerValue="-----请选择----" headerKey="1" list="{'工程师','教师','学生','公务员','白领','蓝领','自由职业者','其他'}"></s:select>
							</td>
						</tr>
						
						
						<tr>
							<td>
								<s:textfield name="phone" label="电话"></s:textfield>
							</td>
						</tr>
						
						<tr>
							<td>
								<s:textfield name="place" label="地址"></s:textfield>
							</td>
						</tr>
						
						<tr>
							<td>
								<s:textfield name="email" label="邮箱"></s:textfield>
							</td>
						</tr>
						
						<tr>
							<td colspan="2" align="center">
								<input type="submit" value="确定"/>
								&nbsp;&nbsp;&nbsp;&nbsp;									
								<input type="reset" value="清空"/>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<s:a href="login/index.jsp">返回</s:a>
								
							</td>
						</tr>

						
					</table>		
	
				</td>
			   </tr>
	
		</table>
	
	</s:form> 
</body>
</html>