package com.blmdlm.personManager.Action;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import DBJavaBean.DB;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author gejing gjblmdlm@sina.com
 * @version Creation Time：2014-6-12 上午1:49:16
 * 处理更新个人信息的Action
 */
public class UpdateMessAction  extends ActionSupport implements ServletRequestAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String name;
	private String sex;
	private String birth;
	private String nation;
	private String edu;
	private String work;
	private String phone;
	private String place;
	private String email;
	private ResultSet resultSet=null;
	private HttpServletRequest request;
	
	@Override
	public String execute() throws Exception{
		DB mysql=new DB();
		userName=mysql.returnLogin(request);
		String mess=mysql.updateMess(request,getUserName(),getName(),getSex(),getBirth(),getNation(),getEdu(),getWork(),getPhone(),getPlace(),getEmail());
		if (mess.equals("ok")) {
			return SUCCESS;
		}else {
			return INPUT;
		}
		
		
	}
	public void validate(){
		if (getName()==null||getName().length()==0) {
			addFieldError("name", "姓名不能为空");
		}
		
		if (getBirth()==null||getBirth().length()==0) {
			addFieldError("birth","生日不能为空");
		}else {
			if (getBirth().length()!=10) {
				addFieldError("birth","生日格式为'yyyy-mm-dd'!");
			}else {
				String an=this.getBirth().substring(4,5);
				String bn=this.getBirth().substring(7,8);
				if (!(an.equals("-"))||!(bn.equals("-"))) {
					addFieldError("birth","生日格式为'yyyy-mm-dd'!");
				}
			}
		}
		
		if (getNation()==null||getNation().length()==0) {
			addFieldError("nation", "民族不能为空");
		}
		if (getEdu().equals("1")) {
			addFieldError("edu", "请选择学历");
		}
		if (getWork().equals("1")) {
			addFieldError("work", "请选择职称");
		}
		if (getPhone()==null||getPhone().length()==0) {
			addFieldError("phone", "电话不能为空");
		}
		if (getPlace()==null||getPlace().length()==0) {
			addFieldError("place", "地址不能为空");
		}
		if (getEmail()==null||getEmail().length()==0) {
			addFieldError("email", "邮箱不能为空");
		}
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getEdu() {
		return edu;
	}
	public void setEdu(String edu) {
		this.edu = edu;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ResultSet getResultSet() {
		return resultSet;
	}
	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		request=arg0;
	}

}
