package com.blmdlm.friendManager.Action;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

import org.apache.struts2.interceptor.ServletRequestAware;

import DBJavaBean.DB;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author gejing gjblmdlm@sina.com
 * @version Creation Time：2014-6-12 上午11:49:08
 * 查找friend的业务控制器类
 */
public class FindFriendAction extends ActionSupport implements ServletRequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String friendName;
	private String userName;
	private ResultSet resultSet=null;
	private HttpServletRequest request;
	
	private void message(String msg){
		int type=JOptionPane.YES_NO_OPTION;
		String title="信息提示";
		JOptionPane.showMessageDialog(null, msg, title,type);
	}
	
	public void validate(){
		if (getFriendName().equals("")||getFriendName().length()==0) {
			message("联系人姓名不能为空");
			addFieldError("friendName", "联系人姓名不能为空");
		}else {
			DB mysql=new DB();
			userName=mysql.returnLogin(request);
			resultSet=mysql.selectFriend(request,userName,getFriendName());
			try {
				if(!resultSet.next()){
					message("联系人姓名不存在");
					addFieldError("friendName","联系人姓名不存在");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public String execute() throws Exception{
		DB mysql=new DB();
		userName=mysql.returnLogin(request);
		String friend=mysql.findFriend(request,userName,getFriendName());
		if (friend.equals("ok")) {
			return SUCCESS;
		}else {
			return input();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	public String getFriendName() {
		return friendName;
	}
	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
