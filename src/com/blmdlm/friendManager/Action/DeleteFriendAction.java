package com.blmdlm.friendManager.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.struts2.interceptor.ServletRequestAware;

import DBJavaBean.DB;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author gejing gjblmdlm@sina.com
 * @version Creation Time：2014-6-12 下午3:40:07
 * 删除好友的业务控制器类
 */
public class DeleteFriendAction extends ActionSupport implements ServletRequestAware {
	private String userName;
	private String name;
	private HttpServletRequest request;
	
	public String execute() throws Exception{
		DB mysql=new DB();
		userName=mysql.returnLogin(request);
		name=mysql.returnFriend(request);
		String del=mysql.deleteFriend(request, userName, name);
		if ("ok".equals(del)) {
			
			return SUCCESS;
		}else {
			return ERROR;
		}
	}
	
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		request=arg0;
		
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

}
