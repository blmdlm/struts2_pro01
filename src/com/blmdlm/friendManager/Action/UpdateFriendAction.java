package com.blmdlm.friendManager.Action;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import DBJavaBean.DB;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author gejing gjblmdlm@sina.com
 * @version Creation Time：2014-6-12 下午3:07:59
 * 修改联系人的业务控制器类
 */
public class UpdateFriendAction extends ActionSupport implements ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String phone;
	private String email;
	private String workplace;
	private String place;
	private String QQ;
	private HttpServletRequest request;
	private ResultSet resultSet=null;
	private String userName;
	private String friendName;
	
	
	public String execute() throws Exception{
		DB mysql=new DB();
		userName=mysql.returnLogin(request);
		friendName=mysql.returnFriend(request);
		String fri=mysql.updateFriend(request,userName,friendName,getName(),getPhone(),getEmail(),getWorkplace(),getPlace(),getQQ());
		if ("ok".equals(fri)) {
			return SUCCESS;
		}else {
			return ERROR;
		}
	}
	
	
	public void validate(){
		if (getName()==null||getName().length()==0) {
			addFieldError("name", "姓名不能为空");
		}
		
		if (getPhone()==null||getPhone().length()==0) {
			addFieldError("phone", "电话不能为空");
		}
		if (getPlace()==null||getPlace().length()==0) {
			addFieldError("place", "家庭地址不能为空");
		}
		if (getEmail()==null||getEmail().length()==0) {
			addFieldError("email", "邮箱不能为空");
		}
		if (getWorkplace()==null||getWorkplace().length()==0) {
			addFieldError("workplace", "工作地址不能为空");
		}
		if(getQQ()==null||getQQ().length()==0){
			addFieldError("QQ", "QQ不能为空");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	public String getFriendName() {
		return friendName;
	}


	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}


	public String getName() {
		return name;
	}











	public void setName(String name) {
		this.name = name;
	}











	public String getPhone() {
		return phone;
	}











	public void setPhone(String phone) {
		this.phone = phone;
	}











	public String getEmail() {
		return email;
	}











	public void setEmail(String email) {
		this.email = email;
	}











	public String getWorkplace() {
		return workplace;
	}











	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}











	public String getPlace() {
		return place;
	}











	public void setPlace(String place) {
		this.place = place;
	}











	public String getQQ() {
		return QQ;
	}











	public void setQQ(String qQ) {
		QQ = qQ;
	}











	public HttpServletRequest getRequest() {
		return request;
	}











	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}











	public ResultSet getResultSet() {
		return resultSet;
	}











	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}











	public String getUserName() {
		return userName;
	}











	public void setUserName(String userName) {
		this.userName = userName;
	}











	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		request=arg0;
	}

}
