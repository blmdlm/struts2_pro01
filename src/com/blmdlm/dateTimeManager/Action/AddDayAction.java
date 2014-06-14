package com.blmdlm.dateTimeManager.Action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

import org.apache.struts2.interceptor.ServletRequestAware;

import DBJavaBean.DB;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author gejing gjblmdlm@sina.com
 * @version Creation Time：2014-6-14 下午3:25:26
 *增加日程对应的业务控制器类 
 */
public class AddDayAction  extends ActionSupport implements ServletRequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String year;
	private String month;
	private String day;
	private String thing;
	private String date;
	private String userName;
	private ResultSet resultSet=null;
	private HttpServletRequest request;
	private  static Date today=new Date();
	
	
	public String execute() throws Exception{
		DB mysql=new DB();
		System.out.println(userName+" "+date);
		String mess=mysql.insertDay(request,userName,date,getThing());
		if ("ok".equals(mess)) {
			return SUCCESS;
		}else {
			return ERROR;
		}
	}
	
	
	
	
	
	
	public void validate(){
		System.out.println("validate()"+today.getMonth()+today.getDate());
		String message="";
		boolean y=true,m=true,d=true,dd=false;
		String time=getTime();
		StringTokenizer token=new StringTokenizer(time,"-");
		if (this.getYear()==null||this.getYear().length()==0) {
			y=false;
			message=message+"*年份";
			addFieldError("year", "年份不允许为空");

		}else if (Integer.parseInt("20"+this.getYear())<today.getYear()||this.getYear().length()!=2) {
			dd=true;
			addFieldError("year"," 请输入正确的年份");
		}
		
		
		
		if (this.getMonth()==null||this.getMonth().length()==0) {
			m=false;
			message=message+"*月份";
			addFieldError("month", "月份不允许为空");

		}else if (Integer.parseInt(getMonth())>12||Integer.parseInt(getMonth())<(today.getMonth()+1)||this.getMonth().length()>2) {
			dd=true;
			addFieldError("month"," 请输入正确的月份");
		}
		
		
		
		if (this.getDay()==null||this.getDay().length()==0) {
			d=false;
			message=message+"*日期";
			addFieldError("day", "日期不允许为空");

		}else if (Integer.parseInt(getDay())<today.getDate()||Integer.parseInt(getDay())>31) {
			dd=true;
			addFieldError("day"," 请输入正确的日期");
		}
		
		
		if(y&&m&&d){
			DB mysql=new DB();
			userName=mysql.returnLogin(request);
			date="20"+this.getYear()+"-"+getMonth()+"-"+getDay();
			resultSet=mysql.selectDay(request,userName,date);
			try {
				if (resultSet.next()) {
					message("该日程已经有安排");
					addFieldError("year","该日程已有安排");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		if (this.getThing()==null||getThing().length()==0) {
			message=message+"*日程安排";
			addFieldError("thing", "日程安排不允许为空");
		}
		
		if(!message.equals("")){
			message=message+"不允许为空";
			message(message);
		}
		if (dd) {
			message("填写的日程无效");
		}
	}
	
	public String getTime(){
		SimpleDateFormat ff=new SimpleDateFormat("yyyy-MM-dd");
		Date d=new Date();
		return ff.format(d);
	}
	
	public void message(String msg){
		int type=JOptionPane.YES_NO_CANCEL_OPTION;
		String title="信息提示";
		JOptionPane.showMessageDialog(null, msg,title,type);
	}
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		request=arg0;
	}



	public String getYear() {
		return year;
	}



	public void setYear(String year) {
		this.year = year;
	}



	public String getMonth() {
		return month;
	}



	public void setMonth(String month) {
		this.month = month;
	}



	public String getDay() {
		return day;
	}



	public void setDay(String day) {
		this.day = day;
	}



	public String getThing() {
		return thing;
	}



	public void setThing(String thing) {
		this.thing = thing;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
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

}
