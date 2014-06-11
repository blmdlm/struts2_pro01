package com.blmdlm.login.Action;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import DBJavaBean.DB;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author gejing gjblmdlm@sina.com
 * @version Creation Time：2014-6-8 下午3:19:12
 * 登录页面对应的业务控制器类
 */
public class LoginAction extends ActionSupport  implements ServletRequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	private ResultSet rs=null;
	private String message=ERROR;
	private HttpServletRequest request;
	
	@Override
	public String execute() throws Exception{
		//ActionContext.getContext()
		System.out.println("用户的输入execute()"+userName+"   "+password);
		DB mysql=new DB();
		System.out.println("request"+request);
		String add=mysql.addList(request,this.getUserName());
		if (add.equals("ok")) {
			return SUCCESS;
		}
		return LOGIN;
	}
	
	
	public void validate(){
		System.out.println("用户的输入validate()"+userName+"   "+password);
		
		
		//验证输入的登录名
		if (this.getUserName()==null||this.getUserName().length()==0) {
			addFieldError("username", "请输入登录名");
		}else {
			System.out.println("userName ok1");
			DB mysql=new DB();
			rs=mysql.selectMess(request,this.getUserName());
			try {
				System.out.println("userName ok2");
				if (!rs.next()) {
					addFieldError("username", "此用户尚未注册！");
				}
				System.out.println("userName ok3");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//验证输入的密码
		if (this.getPassword()==null||this.getPassword().length()==0) {
			addFieldError("password", "请输入登录密码！");
		}else {
			System.out.println("password ok1");
			DB mysql=new DB();
			rs=mysql.selectMess(request, this.getUserName());
			try {
				System.out.println("password ok2");
				if (rs.next()) {
					rs=mysql.selectLogin(request,this.getUserName(),this.getPassword());
					
					if (!rs.next()) {
						addFieldError("password", "登录密码错误！");
					}
					System.out.println("password ok3");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
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





	public String getPassword() {
		return password;
	}





	public void setPassword(String password) {
		this.password = password;
	}


	public ResultSet getRs() {
		return rs;
	}


	public void setRs(ResultSet rs) {
		this.rs = rs;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public HttpServletRequest getRequest() {
		return request;
	}


	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

}
