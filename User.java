package p1;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;
import java.util.Enumeration;

public class User extends HttpServlet{
	
	//private String UserId;
	//private String name;
	//private String password;
	//private String role="customer";
	//private String dob;
	//private String gender,email,address;
	
	
	// function that connects to database and select the username and password
	public boolean signin(String userid,String pass) {
		
		
		String sql_statement="select * from flight.user where uname=? and password=?";
    	String url="jdbc:mysql://localhost:3306/flight?useSSL=false";
    	String root="root";
    	String password="rootadmin";
    	
    	//UserId=userid;
    	//password=pass;
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		
    		// creating connection to database
    		Connection connection =DriverManager.getConnection(url, root, password);
    		
    		PreparedStatement ps =connection.prepareStatement(sql_statement);
    		
    		
    		ps.setString(1, userid);
    		ps.setString(2, pass);
    		
    		
    		ResultSet rs = ps.executeQuery();
    		
    		
    	if(rs.next()) {
    		return true;
    	}
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	return false;
	}
	
	
	// sign un for admin only
	public boolean signin(String userid,String pass,String role) {
		
		String sql1="select * from flight.admin where uname=? and password=?";
		
		
    	String url="jdbc:mysql://localhost:3306/flight?useSSL=false";
    	
    	String root="root";
    	
    	String password="rootadmin";
    	
    	
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		
    		
    		// creating connection to database
    		Connection connection =DriverManager.getConnection(url, root, password);
    		
    		
    		PreparedStatement ps=connection.prepareStatement(sql1);
    		
    		ps.setString(1, userid);
    		ps.setString(2, pass);
    		ResultSet rs=ps.executeQuery();
    	if(rs.next()) {
    		return true;
    	}
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	return false;
	}
	
	// sign un for admin only
	public boolean signup(String name,String email,String uname,String pass) {
		
		
		String sql_statement ="insert into flight.user(name,email,uname,password)"+"values(?,?,?,?)";
    	//String sql1="select * from flight.user";
    	String url="jdbc:mysql://localhost:3306/flight?useSSL=false";
    	String root="root";
    	String password="rootadmin";
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			
			// creating connection to database
			Connection connection = DriverManager.getConnection(url,root,password);
			
			// prepared statement
			PreparedStatement ps = connection.prepareStatement(sql_statement);
			
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, uname);
			ps.setString(4, pass);
			
			int update = ps.executeUpdate();
			
			if(update==1) {
				return true;
			}
    	}
    	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return false;
	}
	
	// function to log out 
	public boolean signout(HttpSession session) {
		
		// if we log out we want to remove the username attribute from the http session
		session.removeAttribute("uname");
		session.invalidate();
		return true;
		
	}
	

}

