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

/**
 * Admin class is implemented as a servlet with action "Admin"
 * 
 * It performs 3 actions:
 * 
 * 1 - Add a flight into the flight table database
 * 2 - Remove a flight from the flight table database
 * 3 - Remove a user from the user table database
 * 
 */


@WebServlet("/Admin")




public class Admin extends User {
	
	// Admin username 
	private String username;
	
	// Admin password 
	private String pass;
	
	// Connection to database using mysql connection
	String url="jdbc:mysql://localhost:3306/airline?useSSL=false";
	
	// root username
	String root="root";
	
	// password to mysql connection
	String password="rootadmin";
	
	
	private static final long serialVersionUID = 1L;
	
	
	
	// function to add flight 
	
       public boolean add_flight(HttpServletRequest request,HttpServletResponse response) {
    	   
    	   // id and day information
    	   int cid,rid,dom,mon,tue,wed,thu,fri,sat,sun;
    	   
    	   // store the flight times
    	   String start,end;
    	   
    	   // store the prices
    	   float price1,price2;
    	   
    	   
    	   // get the prices from the Http session
    	   cid=Integer.parseInt(request.getParameter("cid").toString());
    	   rid=Integer.parseInt(request.getParameter("rid").toString());
    	   dom=Integer.parseInt(request.getParameter("dom").toString());
    	   mon=Integer.parseInt(request.getParameter("mon").toString());
    	   tue=Integer.parseInt(request.getParameter("tue").toString());
    	   wed=Integer.parseInt(request.getParameter("wed").toString());
    	   thu=Integer.parseInt(request.getParameter("thu").toString());
    	   fri=Integer.parseInt(request.getParameter("fri").toString());
    	   sat=Integer.parseInt(request.getParameter("sat").toString());
    	   sun=Integer.parseInt(request.getParameter("sun").toString());
    	   
    	   
    	   // get seats for each day of the week for economy and business from the Http session
    	   int mon_seats=Integer.parseInt(request.getParameter("mon_seats").toString());
    	   int tue_seats=Integer.parseInt(request.getParameter("tue_seats").toString());
    	   int wed_seats=Integer.parseInt(request.getParameter("wed_seats").toString());
    	   int thu_seats=Integer.parseInt(request.getParameter("thu_seats").toString());
    	   int fri_seats=Integer.parseInt(request.getParameter("fri_seats").toString());
    	   int sat_seats=Integer.parseInt(request.getParameter("sat_seats").toString());
    	   int sun_seats=Integer.parseInt(request.getParameter("sun_seats").toString());
    	   int mon_seats_bus=Integer.parseInt(request.getParameter("mon_seats_eco").toString());
    	   int tue_seats_bus=Integer.parseInt(request.getParameter("tue_seats_eco").toString());
    	   int wed_seats_bus=Integer.parseInt(request.getParameter("wed_seats_eco").toString());
    	   int thu_seats_bus=Integer.parseInt(request.getParameter("thu_seats_eco").toString());
    	   int fri_seats_bus=Integer.parseInt(request.getParameter("fri_seats_eco").toString());
    	   int sat_seats_bus=Integer.parseInt(request.getParameter("sat_seats_eco").toString());
    	   int sun_seats_bus=Integer.parseInt(request.getParameter("sun_seats_eco").toString());
    	   
    	   
    	   // get the departure time 
    	   start=request.getParameter("start").toString();
    	   
    	   // get the arrival time
    	   end=request.getParameter("end").toString();
    	   
    	   // get the price 
    	   price1=Float.parseFloat(request.getParameter("price1").toString());
    	   price2=Float.parseFloat(request.getParameter("price2").toString());
    	   
    	   
    	   
    	   try {
    		   
    	    	Class.forName("com.mysql.jdbc.Driver");
    	    	
    	    	// define the sql statement as a string
    	    	String sql="insert into flight(company_id,route_id,start_time,dest_time,seat_eco,seat_bus,price,domestic,mon,tue,wed,thu,fri,sat,sun,price2,mon_seats,tue_seats,wed_seats,thu_seats,fri_seats,sat_seats,sun_seats,mon_seats_bus,tue_seats_bus,wed_seats_bus,thu_seats_bus,fri_seats_bus,sat_seats_bus,sun_seats_bus) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    	    	   
    	        // create the connection
        		Connection connection =DriverManager.getConnection(url, root, password);
        		
        		
        		// prepare the sql statement
        		PreparedStatement st = connection.prepareStatement(sql);
        		
        		
        		
        		// set the values in the sql statement
        		st.setInt(1, cid);
        		st.setInt(2, rid);
        		st.setString(3, start);
        		st.setString(4, end);
        		st.setInt(5, 0);
        		st.setInt(6, 0);
        		st.setFloat(7, price1);
        		st.setInt(8, dom);
        		st.setInt(9, mon);
        		st.setInt(10, tue);
        		st.setInt(11, wed);
        		st.setInt(12, thu);
        		st.setInt(13, fri);
        		st.setInt(14, sat);
        		st.setInt(15, sun);
        		st.setFloat(16, price2);
        		st.setInt(17, mon_seats);
        		st.setInt(18, tue_seats);
        		st.setInt(19, wed_seats);
        		st.setInt(20, thu_seats);
        		st.setInt(21, fri_seats);
        		st.setInt(22, sat_seats);
        		st.setInt(23, sun_seats);
        		st.setInt(24, mon_seats_bus);
        		st.setInt(25, tue_seats_bus);
        		st.setInt(26, wed_seats_bus);
        		st.setInt(27, thu_seats_bus);
        		st.setInt(28, fri_seats_bus);
        		st.setInt(29, sat_seats_bus);
        		st.setInt(30, sun_seats_bus);
        		
        		
        		int i=st.executeUpdate();
        		if(i>0)
        			return true;
        		
    	   }
    	   catch(Exception e) {
    		   e.printStackTrace();
    	   }
    	   return false;
       }
       
       
       
       // Function to remove flight
       
       public boolean remove_flight(HttpServletRequest request, HttpServletResponse response) {
    	   
    	   // get the flight id to be removed 
    	   int flight_id=Integer.parseInt(request.getParameter("fid").toString());
    	   
    	   try {
    		   
    	    	Class.forName("com.mysql.jdbc.Driver");
    	    	
    	    	// create the sql statement as a string
    	    	String sql="delete from flight where flight.id=?";
        		
    	    	// create a mysql connection
    	    	Connection connection =DriverManager.getConnection(url, root, password);
    	    	
    	    	
    	    	// prepare the sql statement
        		PreparedStatement st = connection .prepareStatement(sql);
        		
        		// set the flight id in the sql statement
        		st.setInt(1, flight_id);
        		
        		// get the response of whether 
        		int resp = st.executeUpdate();
        		
        		// if it's not zero it means the update worked
        		if(resp > 0 ) {
        			return true;
        		}
    	   }
    	   catch(Exception e) {
    		   
    	   }
    	   return false;
       }
       
       
       
       
       // Function remove user
       public boolean remove_user(HttpServletRequest request, HttpServletResponse response) {
    	   
    	   
    	   int user_id=Integer.parseInt(request.getParameter("uid").toString());
    	
    	   try {
   
    	    	Class.forName("com.mysql.jdbc.Driver");
    	    	
    	    	// create the sql statement as a string
    	    	String sql="delete from user where user.id=?";
    	    	   
        		
    	    	// create a mysql connection
    	    	Connection connection =DriverManager.getConnection(url, root, password);
    	    	
    	    	
    	    	// prepare the sql statement
        		PreparedStatement st = connection .prepareStatement(sql);
        		
        		// set the flight id in the sql statement
        		st.setInt(1, user_id);
        		
        		int resp =st.executeUpdate();
        		
        		if(resp >0) {
        			return true;
        		}
    	   }
    	   catch(Exception e) {
    		   
    	   }
    	   return false;
       }
       
    
       
       // Do post function
    	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		
    		// get the process
    		String process =request.getParameter("s1");
    		
    		// get the http session
    		HttpSession session=request.getSession();
    		
    		// process sign in
    		if(process.equals("signin")) {
    			
    			// get username and password
    			username=request.getParameter("username").toString();
    			pass=request.getParameter("pass").toString();
    			
    			// if it's an admin 
    			if(signin(username,pass,"admin")) {
    				
    				// use a printwriter object so we can output messages using alert or redirect uding location.href
    				PrintWriter out=response.getWriter();
    				
    				session.setAttribute("uname", username);
    				response.sendRedirect("adminhome.jsp");
    				session.setMaxInactiveInterval(600);
					out.print("<script>window.location.href='adminhome.jsp';</script>");
				
    			}
    			else {
    				PrintWriter out=response.getWriter();
					out.print("<script>window.alert('Password does not match the admin's');</script>");
					out.print("<script>window.location.href='adminlogin.jsp';</script>");
				
    			}
    		}
    		// if add flight returns true
    		if(process.equals("Add Flight")) {
    			if(add_flight(request,response)) {
    				PrintWriter out=response.getWriter();
    				out.println("<script>alert('Successfully added Flight')</script>");
    				out.print("<script>window.location.href='adminhome.jsp';</script>");
				
    			}
    		}
    		
    		
    		if(process.equals("Remove Flight")) {
    			//if remove flight returns true
    			if(remove_flight(request,response)) {
    				PrintWriter out=response.getWriter();
    				out.println("<script>alert('Successfully removed Flight')</script>");
    				out.print("<script>window.location.href='adminhome.jsp';</script>");
				
    			}
    		}
    		
    		if(process.equals("Remove User")) {
    			// if remove user returns true
    			if(remove_user(request,response)) {
    				PrintWriter out=response.getWriter();
    				out.println("<script>alert('Successfully removed User')</script>");
    				out.print("<script>window.location.href='adminhome.jsp';</script>");
				
    			}
    		
    		}
    		
    	
    	}

}
