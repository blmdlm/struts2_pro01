package com.blmdlm.personManager.Action;

import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

import org.apache.struts2.interceptor.ServletRequestAware;

import DBJavaBean.DB;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author gejing gjblmdlm@sina.com
 * @version Creation Time：2014-6-12 上午9:45:11
 * 处理更新密码的ACTION
 */
public class UpdatePassAction extends ActionSupport implements ServletRequestAware {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private String password1;
		private String password2;
		private String userName;
		private HttpServletRequest request;
		
		public void message(String msg){
			int type=JOptionPane.YES_NO_OPTION;
			String title="信息提示";
			JOptionPane.showMessageDialog(null, msg,title,type);
		}
		
		public void validate(){
			if (getPassword1()==null||getPassword1().length()==0) {
				message("密码不能为空");
				addFieldError("password1", "登录密码不能为空");
			}
			if (!password1.equals(password2)) {
				message("两次密码不同！");
				addFieldError("password2", "两次密码不同");
			}
		}
		
		public String execute() throws Exception{
			DB mysql=new DB();
			userName=mysql.returnLogin(request);
			String pass=mysql.updatePass(request, userName, getPassword1());
			if (pass.equals("ok")) {
				return SUCCESS;
			}else {
				return INPUT;
			}
		}

	public String getPassword1() {
			return password1;
		}

		public void setPassword1(String password1) {
			this.password1 = password1;
		}

		public String getPassword2() {
			return password2;
		}

		public void setPassword2(String password2) {
			this.password2 = password2;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
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
