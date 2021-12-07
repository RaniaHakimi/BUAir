package p1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Passenger")

// class is for adding a user to the database in flight.passanger table

// There are two functions add, one has a int prime and one does not both have random 

/*
 * Passenger class implemented as servlet with action value "Passenger"
 * This class has the add method that adds a primary passenger and also a secondary if applicable to the passenger table
 */


public class Passenger extends HttpServlet {
	
	// variables to store passenger info
	private int age;
	private String name;
	private String gender;
	private static final long serialVersionUID = 1L;
	
	// setting the url for mysql server connection
	String url="jdbc:mysql://localhost:3306/airline?useSSL=false";
	
	 // setting root variable
     String root="root";
     // setting password
	 String password="rootadmin";
	 
	 
	   // Add function that adds the primary passenger in the passenger table
	
       public boolean add(HttpServletRequest request,HttpSession ses,String uname,String flight_class ,int prime,int random) {
    	     
   		
   		// store the sql statement
   		String sql_statement ="insert into passenger(name,age,gender,class,prime,uname,random) values(?,?,?,?,?,?,?)";
   		
   		try{
   			
   			
   			Class.forName("com.mysql.jdbc.Driver");
   			
   			// create connection to the database
   			Connection connection =DriverManager.getConnection(url, root, password);
   			
   			// prepare sql statement
   			PreparedStatement ps = connection.prepareStatement(sql_statement);
   			
   			
   			// get the passanger name from request
   			name = request.getParameter("pname").toString();
   			
   			// get the age from request
   		    age = Integer.parseInt(request.getParameter("age").toString());
   		    
   		    // get gender from request
   		    gender=request.getParameter("gender").toString();
   			
   		    	// set variables in the prepared stateement
   				ps.setString(1, name);
   				ps.setInt(2, age);
   				ps.setString(3, gender);
   				ps.setString(4, flight_class);
   				
   				// set int prime
   				ps.setInt(5, prime);
   				ps.setString(6,uname);
   				ps.setInt(7, random);
   				
   				// execute sql statement
   				int response =ps.executeUpdate();
   				
   				// if response is 1 it means the sql statement executed successfully 
   				if(response == 1) {
   					return true;
   				}
   				
    	
       }
   		catch(Exception e) {
   			
   		}
   	  return false;
       }
       
       
       // Function that adds a secondary passenger
       public boolean add(HttpServletRequest request,HttpSession ses,String uname,String flight_class,int random) {
    	   
    	
   		// sql statement
   		String sql_statement ="insert into passenger(name,age,gender,class,prime,uname,random) values(?,?,?,?,?,?,?)";
   		
   		// try catch block of code
   		try{
   			Class.forName("com.mysql.jdbc.Driver");
   			
   			// create connection to the database
   			Connection connection =DriverManager.getConnection(url, root, password);
   			
   			
   			PreparedStatement ps = connection.prepareStatement(sql_statement);
   			
   			// store the name 
   			String pname=request.getParameter("pname").toString();
   			
   			// store the age
   			int age=Integer.parseInt(request.getParameter("age").toString());
   			
   			// store gender 
   			
   			String gender=request.getParameter("gender").toString();
   			
   			// set the variables (?) in sql statement
   			ps.setString(1, pname);
   			ps.setInt(2, age);
   			ps.setString(3, gender);
   			ps.setString(4, flight_class);
   				
   			// setting the primary passenger to 0 because it is secondary 
   			ps.setInt(5,0);
   			
   			ps.setString(6,uname);
   			ps.setInt(7, random);
   				
   				
   			int response = ps.executeUpdate();
   				
   				
   			if(response == 1) {
   				return true;
   			}
   				
    	
       }
   		catch(Exception e) {
   			
   		}
   	  return false;
      }
       
       
       // send a response
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		
		
		HttpSession session =request.getSession();
		
		// get action parameter 
		String add_flight=request.getParameter("add").toString();
		
		// get username from session
		String username=session.getAttribute("uname").toString();
		
		// get class type from session
		String flight_class=session.getAttribute("class").toString();
		
		// get random parameter from session
		int random=Integer.parseInt(session.getAttribute("random").toString());
				
				
		PrintWriter out=response.getWriter();	
		// if the variable is "Add" then we add a secondary passenger
		if(add_flight.equals("Add")) {
			
		if(add(request,session,username,flight_class,random)) {
			
			// print a message 
			out.print("<script>alert('Secondary Passanger"
						+ " added successfully')</script>");
				out.print("<script>location.href='add.jsp'</script>");
				
			}
		}
		
		
		// if the variable is "add" then we add a primary passenger
		if(add_flight.equals("add")) {
			
			if(add(request,session,username,flight_class,1,random)) {
				
				// print a message 
				out.print("<script>alert('Primary Passenger"
							+ " added successfully')</script>");
					out.print("<script>location.href='add.jsp'</script>");
			}}
	}
}