package DBJavaBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.blmdlm.JavaBean.MyDayBean;
import com.blmdlm.JavaBean.MyFileBean;
import com.blmdlm.JavaBean.MyFriBean;
import com.blmdlm.JavaBean.MyMessBean;
import com.blmdlm.JavaBean.UserBean;
import com.sun.crypto.provider.RSACipher;
import com.sun.media.sound.FFT;
import com.sun.org.apache.xpath.internal.operations.And;

/**
 * @author gejing gjblmdlm@sina.com
 * @version Creation Time：2014-6-8 下午3:30:55
 * 进行数据库连接并对数据库中的数据进行操作
 */
public class DB implements ServletRequestAware {
	
	private String url="jdbc:mysql://localhost:3306/personmessage";
	private String  user="root";
	private String password="mysqlroot";
	private Connection connection=null;
	private Statement statement=null;
	private ResultSet resultSet=null;
	private HttpServletRequest request;
	private String driverName="com.mysql.jdbc.Driver";
	public DB(){
		
	}
	//完成连接数据库操作，生成容器并返回
	public Statement getStatement(){
		try {
			Class.forName(getDriverName());
			connection=DriverManager.getConnection(getUrl(),getUser(),getPassword());
			return connection.createStatement();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 查询个人信息，并返回结果集
	 * @param request
	 * @param userName
	 * @return
	 */
	public ResultSet selectMess(HttpServletRequest request, String userName) {
		String sql="select * from user where userName='"+userName+"'";
		statement=getStatement();
		try {
			return statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	/**
	 * 查询登录名和密码是否存在
	 * @param request
	 * @param userName
	 * @param password
	 * @return
	 */
	public ResultSet selectLogin(HttpServletRequest request, String userName,
			String password) {
		String sql="select * from user where userName='"+userName+"' and password='"+password+"' ";
		statement=getStatement();
		try {
			return statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 调用myLogin()、myMessage()、myFriends()、myDayTime()、myFile方法，把所有和用户有关的信息
	 * 全部保存到session对象中。该方法在登录成功后调用
	 * @param request
	 * @param userName
	 * @return
	 */
	public String addList(HttpServletRequest request, String userName) {
		String sure=null;
		String login=myLogin(request,userName);
		String mess=myMessage(request,userName);
		String fri=myFriends(request,userName);
		String day=myDayTime(request,userName);
		String file=myFile(request,userName);
		if (login.equals("ok")&&mess.equals("ok")&&fri.equals("ok")&&day.equals("ok")&&file.equals("ok")) {
			sure="ok";
		}else {
			sure=null;
		}
		return sure;
	}
	/**
	 * 查询所有的文件信息，将其保存到session中
	 * @param request
	 * @param userName
	 * @return
	 */
	private String myFile(HttpServletRequest request, String userName) {
		ArrayList listName=null;
		HttpSession session=request.getSession();
		listName=new ArrayList();
		resultSet=selectFileAll(request,userName);
		try {
			if (resultSet.next()) {
				resultSet=selectFileAll(request,userName);
				while(resultSet.next()){
					MyFileBean mess=new MyFileBean();
					mess.setTitle(resultSet.getString("title"));
					mess.setName(resultSet.getString("name"));
					mess.setContentType(resultSet.getString("contentType"));
					mess.setSize(resultSet.getString("size"));
					listName.add(mess);
					session.setAttribute("file", listName);

				}
			}else {
				session.setAttribute("file", listName);
			}
			return "ok";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	/**
	 * 查询所有文件的信息
	 * @param request
	 * @param userName
	 * @return
	 */
	private ResultSet selectFileAll(HttpServletRequest request, String userName) {
		String sql="select * from file where userName ='"+userName+"'";
		statement=getStatement();
		try {
			return statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	/**
	 * 查询所有日程信息，并把它们保存在session中
	 * @param request
	 * @param userName
	 * @return
	 */
	private String myDayTime(HttpServletRequest request, String userName) {
		ArrayList listName=null;
		HttpSession session=request.getSession();
		listName=new ArrayList();
		resultSet=selectDayAll(request,userName);
		try {
			if (resultSet.next()){
				resultSet=selectDayAll(request, userName);
				while(resultSet.next()){
					MyDayBean mess=new MyDayBean();
					mess.setDay(resultSet.getString("date"));
					mess.setThing(resultSet.getString("thing"));
					listName.add(mess);
					session.setAttribute("day", listName);
				}
			}else {
				session.setAttribute("day", listName);
			}
			return "ok";
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	
	}
	/**
	 * 查询所有的日程信息
	 * @param request
	 * @param userName
	 * @return
	 */
	private ResultSet selectDayAll(HttpServletRequest request, String userName) {
		String sql="select * from date where userName='"+userName+"'";
		statement=getStatement();
		try {
			return statement.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	/**
	 * 获取通讯录中所有联系人的信息，并将它们保存到session对象中
	 * @param request
	 * @param userName
	 * @return
	 */
	private String myFriends(HttpServletRequest request, String userName) {
		ArrayList listName=null;
		HttpSession session=request.getSession();
		listName=new ArrayList();
		resultSet=selectFriAll(request,userName);
		try {
			while(resultSet.next()) {
				
				MyFriBean mess=new MyFriBean();
				mess.setName(resultSet.getString("name"));
				mess.setPhone(resultSet.getString("phone"));
				mess.setWorkplace(resultSet.getString("workplace"));
				mess.setPlace(resultSet.getString("place"));
				mess.setQQ(resultSet.getString("QQ"));
				mess.setEmail(resultSet.getString("email"));
				listName.add(mess);
				
			}
				session.setAttribute("friends", listName);
				
			return "ok";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	
	}
	/**
	 * 获取所有联系人的信息
	 * @param request
	 * @param userName
	 * @return
	 */
	private ResultSet selectFriAll(HttpServletRequest request, String userName) {
		String sql="select * from friends where userName='"+userName+"'";
		statement=getStatement();
		try {
			return statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	/**
	 * 把个人信息通过myMessBean保存到session对象中
	 * @param request
	 * @param userName
	 * @return
	 */
	private String myMessage(HttpServletRequest request, String userName) {
		ArrayList listName=null;
		HttpSession session=request.getSession();
		listName=new ArrayList();
		resultSet=selectMess(request, userName);
		try {
			while (resultSet.next()) {
				MyMessBean mess=new MyMessBean();

					mess.setName(resultSet.getString("name"));
					mess.setSex(resultSet.getString("sex"));
					mess.setBirth(resultSet.getString("birth"));
					mess.setNation(resultSet.getString("nation"));
					mess.setEdu(resultSet.getString("edu"));
					mess.setWork(resultSet.getString("work"));
					mess.setPhone(resultSet.getString("phone"));
					mess.setPlace(resultSet.getString("place"));
					mess.setEmail(resultSet.getString("email"));
					listName.add(mess);
					session.setAttribute("MyMess", listName);
		
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return "ok";

	}
	/**
	 * 把登陆用户的信息保存到session对象中
	 * @param request
	 * @param userName
	 * @return
	 */
	private String myLogin(HttpServletRequest request, String userName) {
		ArrayList listName=null;
		System.out.println("request"+request);
		HttpSession session=request.getSession();
		System.out.println("session"+session);
		listName=new ArrayList();
		resultSet=selectMess(request, userName);
		try {
			if (resultSet.next()) {
				resultSet=selectMess(request, userName);
				while(resultSet.next()){
					System.out.println("123***");
					UserBean currentUser=new UserBean();
					currentUser.setUserName(resultSet.getString("userName"));
					currentUser.setPassword(resultSet.getString("password"));
					listName.add(currentUser);
					session.setAttribute("userName", listName);
					return "ok";
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
		return null;
	}
	/**
	 * 完成注册，把用户的信息录入到数据库中
	 * @param request
	 * @param userName
	 * @param password1
	 * @param name
	 * @param sex
	 * @param birth
	 * @param nation
	 * @param edu
	 * @param work
	 * @param phone
	 * @param place
	 * @param email
	 * @return
	 */
	public String insertMess(HttpServletRequest request, String userName,
			String password1, String name, String sex, String birth,
			String nation, String edu, String work, String phone, String place,
			String email) {
		resultSet=selectMess(request, userName);
		try {
			if (resultSet.next()) {
				return "one";
			}else {
				String sql="insert into user(userName,password,name,sex,birth,nation,edu,work,phone,place,email) values("+
			"'"+userName+"',"+
			"'"+password1+"',"+
			"'"+name+"',"+
			"'"+sex+"',"+
			"'"+birth+"',"+
			"'"+nation+"',"+
			"'"+edu+"',"+
			"'"+work+"',"+
			"'"+phone+"',"+
			"'"+place+"',"+
			"'"+email+"');";
				statement=getStatement();
				int row=statement.executeUpdate(sql);
				if (row==1) {
					String mess=myMessage(request, userName);
					if (mess.equals("ok")) {
						return "ok";
					}else {
						return null;
					}
				}else {
					return null;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	/**
	 * 返回登录用户的用户名
	 * @param request2
	 * @return
	 */
	public String returnLogin(HttpServletRequest request) {
		String loginName=null;
		HttpSession session=request.getSession();
		ArrayList login=(ArrayList)session.getAttribute("userName");
		if (login==null||login.size()==0) {
			return null;
		}else {
			for(int i=login.size()-1;i>=0;i--){
				UserBean u=(UserBean)login.get(i);
				loginName=u.getUserName();
				
			}
			return loginName;
		}
	}
	/**
	 * 更新注册的个人信息
	 * @param request
	 * @param userName
	 * @param name
	 * @param sex
	 * @param birth
	 * @param nation
	 * @param edu
	 * @param work
	 * @param phone
	 * @param place
	 * @param email
	 * @return
	 */
	public String updateMess(HttpServletRequest request, String userName,
			String name, String sex, String birth, String nation, String edu,
			String work, String phone, String place, String email) {
		String sql="update user set name='"+name+"',sex='"+sex+"',birth='"+birth+"',nation='"+nation+"',edu='"+edu+"',work='"+work+"',phone='"+phone+"',place='"+place+"',email='"+email+"' where userName= '"+userName+"' ;  ";
		statement=getStatement();
		try {
			int row=statement.executeUpdate(sql);
			System.out.println("row"+row);
			if (row==1) {
				String mess=myMessage(request, userName);
				if (mess.equals("ok")) {
					return "ok";
				}else {
					return null;
				}
			}else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 修改用户密码
	 * @param request
	 * @param userName
	 * @param password
	 * @return
	 */
	public String updatePass(HttpServletRequest request,String userName,String password){
		String sql="update user set password='"+password+"' where userName='"+userName+"';";
		statement=getStatement();
		int row;
		try {
		 row = statement.executeUpdate(sql);
	
		if(row==1){
			String mess=myLogin(request, userName);
			if (mess.equals("ok")) {
				return "ok";
			}else {
				return null;
			}
		}else {
			return null;
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	////////////////////////////////////////////////////////////////////////通讯录管理部分//////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 查询联系人
	 * @param request
	 * @param userName
	 * @param friendName
	 * @return
	 */
	public ResultSet selectFriend(HttpServletRequest request, String userName,
			String friendName) {
		String sql="select * from friends where userName='"+userName+"' and name='"+friendName+"'; ";
		statement=getStatement();
		try {
			return statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	/**
	 * 查找联系人，并将其信息保存到session对象中
	 * @param request
	 * @param userName
	 * @param friendName
	 * @return
	 */
	public String findFriend(HttpServletRequest request, String userName,
			String friendName) {
		ArrayList listName=null;
		HttpSession session=request.getSession();
		listName= new ArrayList();
		resultSet=selectFriend(request, userName, friendName);
		try {
			if(resultSet.next()){
				resultSet=selectFriend(request, userName, friendName);
				while(resultSet.next()){
					MyFriBean mess=new MyFriBean();
					mess.setName(resultSet.getString("name"));
					mess.setPhone(resultSet.getString("phone"));
					mess.setEmail(resultSet.getString("email"));
					mess.setWorkplace(resultSet.getString("workplace"));
					mess.setPlace(resultSet.getString("place"));
					mess.setQQ(resultSet.getString("QQ"));
					listName.add(mess);
					session.setAttribute("findFriend", listName);
				}
			}else {
				session.setAttribute("findFriend", listName);
			}
			return "ok";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	/**
	 * 添加联系人
	 * @param request
	 * @param userName
	 * @param name
	 * @param email
	 * @param workplace
	 * @param place
	 * @param qq
	 * @param phone
	 * @return
	 */
	public String insertFriend(HttpServletRequest request, String userName,
			String name, String email, String workplace, String place,
			String qq, String phone) {
		System.out.println(name+email+workplace+place+phone+qq);
		
		
		//由于在验证部分已经判断联系人已经存在，所以不再验证
		String sql="insert into friends(userName,name,phone,email,workplace,place,QQ) values('"+userName+"','"+name+"','"+phone+"','"+email+"','"+workplace+"','"+place+"','"+qq+"');";
		statement=getStatement();
		int row;
		try {
			row = statement.executeUpdate(sql);
	
		if (row==1) {
			//更新session中通讯录的信息
			String fri=myFriends(request, userName);
			if(fri.equals("ok")){
				return "ok";
			}else {
				return null;
			}
		}else {
			return null;
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	
	}

	/**
	 * 修改联系人
	 * @param request
	 * @param userName
	 * @param friendName
	 * @param name
	 * @param phone
	 * @param email
	 * @param workplace
	 * @param place
	 * @param qq
	 * @return
	 */
	public String updateFriend(HttpServletRequest request, String userName,
			String friendName, String name, String phone, String email,
			String workplace, String place, String qq) {
		String del=deleteFriend(request,userName,friendName);
		if ("ok".equals(del)) {
			//重新录入信息
			String in=insertFriend(request, userName, name, email, workplace, place, qq, phone);
			if ("ok".equals(in)) {
				String fri=myFriends(request, userName);
				//
				
				if ("ok".equals(fri)) {
					return "ok";
				}else {
					return null;
				}
			}else {
				return null;
			}
		}else {
			return null;
		}
	
	}
	
	
	
	/**
	 * 删除联系人
	 * @param request
	 * @param userName
	 * @param friendName
	 * @return
	 */
	public String deleteFriend(HttpServletRequest request, String userName,
			String friendName) {
		
			String sql="delete from friends where userName='"+userName+"' and name='"+friendName+"';";
		statement=getStatement();
		int row;
		try {
			row = statement.executeUpdate(sql);
			
		if (row==1) {
			String fri=myFriends(request, userName);
			HttpSession session=request.getSession();
			session.removeAttribute("findFriend");	
			if ("ok".equals(fri)) {
				return "ok";
			}else {
				return null;
			}
		}else {
			return null;
		}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	/**
	 * 从查找到的联系人session对象中获取联系人姓名，并返回
	 * @param request
	 * @return
	 */
	public String returnFriend(HttpServletRequest request) {
		HttpSession session=request.getSession();
		ArrayList login=(ArrayList)session.getAttribute("findFriend");
		if (login==null||login.size()==0) {
			return null;
		}else {
			for(int i=login.size()-1;i>=0;i--){
				MyFriBean ff=(MyFriBean)login.get(i);
				return ff.getName();                   //这个地方处理得不好
			}
			
		}
		
		
		return null;
	}
	
	
	
	
	
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		request=arg0;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Connection getConnection() {
		return connection;
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
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
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public void setStatement(Statement statement) {
		this.statement = statement;
	}
	






}
