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
	
	/*
	 * User class handles the sign in for admin, sign in for user and sign up operations
	 */
	
	// Variables to hold user attributes 
	private String name;
	private String userID;
	private String pass;
	private String gender;
	private String emailaddress;
	
	
	// setting the url for mysql server connection
	String url="jdbc:mysql://localhost:3306/airline?useSSL=false";
	// root username
	String root="root";
	// password to mysql connection
	String password="rootadmin";
	
	
	// sign un for admin only
	public boolean signin(String userid,String pass,String role) {
		
		// Create select sql statement as string 
		String sql_statement="select * from admin where uname=? and password=?";
		
		
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		
    		
    		// creating connection to database
    		Connection connection =DriverManager.getConnection(url, root, password);
    		
    		// prepare sql statement
    		PreparedStatement ps=connection.prepareStatement(sql_statement);
    		
    		// set parameters in sql statement
    		ps.setString(1, userid);
    		ps.setString(2, pass);
    		
    		// execute update 
    		ResultSet rs=ps.executeQuery();
    		
    	// since it's a select sql statement the response will be in the form of data
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
		
		// Create Insert sql statement as string 
		String sql_statement ="insert into user(name,email,uname,password) values(?,?,?,?)";
    	
    	
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			
			// creating connection to database
			Connection connection = DriverManager.getConnection(url,root,password);
			
			// prepared statement
			PreparedStatement ps = connection.prepareStatement(sql_statement);
			
			// set variables
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
	
	
	// function that connects to database and select the username and password
	public boolean signin(String userid,String pass) {
			
		// Create select sql statement as string 
			String sql_statement="select * from user where uname=? and password=?";
	    	
	    	
	    	try {
	    		Class.forName("com.mysql.jdbc.Driver");
	    		
	    		// create connection to database
	    		Connection connection =DriverManager.getConnection(url, root, password);
	    		
	    		PreparedStatement ps =connection.prepareStatement(sql_statement);
	    		
	    		// set parameters to the question marks
	    		ps.setString(1, userid);
	    		ps.setString(2, pass);
	    		
	    		
	    		ResultSet rs = ps.executeQuery();
	    		
	    		// since it's a select sql statement the response will be in the form of data
	    	if(rs.next()) {
	    		return true;
	    	}
	    	}
	    	catch(Exception e) {
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

