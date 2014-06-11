package com.blmdlm.JavaBean;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

/**
 * @author gejing gjblmdlm@sina.com
 * @version Creation Time：2014-6-10 下午3:32:43
 * 
 */
public class MyDayBean {
	private String Day;
	private String thing;
	private ResultSet resultSet=null;
	private HttpServletRequest request;
	
	public String getDay() {
		return Day;
	}
	public void setDay(String day) {
		Day = day;
	}
	public String getThing() {
		return thing;
	}
	public void setThing(String thing) {
		this.thing = thing;
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
