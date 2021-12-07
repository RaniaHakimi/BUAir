package p1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Cancel")

/*
 * Cancel class implemented as servlet with action value "Cancel"
 * This class has the cancel method that given a ticket id removes the ticket from the Ticket table
 */


public class Cancel extends HttpServlet {
	
	// setting the url for sql server connection
	String url="jdbc:mysql://localhost:3306/airline?useSSL=false";
	// setting root variable
 	String root="root";
 	// setting password
 	String password="rootadmin";
	
	private static final long serialVersionUID = 1L;
	
	   
	   private int ticket_id;
		
		
		
	   // cancelTicket that deletes ticket from table given a ticket id
       public boolean cancelTicket(int ticket_id) {
    	   
    	   
	    	// create sql statement as string
	    	String sql1="delete from ticket where ticket.random=?";
	    	
	    	// Select sql statement that selects the flight information of the ticket that has been deleted so we can update the seats 
	    	String sql2="select ticket.airplane_id,ticket.seats,passenger.class from ticket inner join passenger on passenger.random=ticket.random where ticket.random=? and passenger.prime=?";
	    	
	    	String sql3=null;
	    	
	    	try {
	    		Class.forName("com.mysql.jdbc.Driver");
	    		
	    		// create connection object
	    		Connection connection =DriverManager.getConnection(url, root, password);
	    		
	    		// Prepare statement to delete ticket id
	    		PreparedStatement st1=connection.prepareStatement(sql1);
	    		
	    		// Prepare statement to update the seats after cancelling ticket
	    		PreparedStatement st2=connection.prepareStatement(sql2);
	    		
	    		
	    		// Set the ticket_id and primary passenger in question marks
	    		st2.setInt(1,ticket_id);
	    		st2.setInt(2, 1);
	    		
	    		// collect the result from the query 
	    		ResultSet result=st2.executeQuery();
	    		
	    		// iterate through the result
	    		if(result.next()) {
	    			
	    			// get the airplane id 
	    			int airplane_id=result.getInt(1);
	    			
	    			// get the seats
	    			int seats=result.getInt(2);
	    			
	    			// get the flight type
	    			String flight_type =result.getString(3).toString();
	    			
	    			// if it's economy
	    			if(flight_type.equals("economy")) {
	    				
	    				sql3="update airplane set seat_eco=seat_eco+? where airplane.id=?";
	    			}
	    			// if it's business 
	    			else {
	    				sql3="update airplane set seat_bus=seat_bus+? where airplane.id=?";
	    			}
	    			
	    			// prepare sql statement
	    			PreparedStatement st3 = connection.prepareStatement(sql3);
	    			
	    			// set the ticket id in the sql statement
	    			st1.setInt(1, ticket_id);
	    			
	    			// get response from update
	    			int response1=st1.executeUpdate();
	    			
	    			// if the update returned 1 (true) execute third sql statement
	    			if(response1>0) {
	    				
	    				st3.setInt(1, seats);
	    				st3.setInt(2, airplane_id);
	    				int response =st3.executeUpdate();
	    				
	    				
	    				if(response>0) {
	    				return true;}
	    			}}
	    	}
	    		catch(Exception e) {
	    		}
    	   return false;
       }
       
       
       // do post function to return the alert if cancel was effective
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ticket_id=request.getParameter("tid").toString();		
		PrintWriter out=response.getWriter();
		                    
							// if ticket id is empty display error message
		                    if (ticket_id == "") {
		                    	out.print("<script>window.alert('Enter a valid ticket ID');</script>");
		    					out.print("<script>window.location.href='cancel.jsp';</script>");
		                    }
		                    else {
		                    	// call cancel function
		                    	int tid = Integer.parseInt(ticket_id);
		                    	if (cancelTicket(tid)) {
		    					out.print("<script>window.alert('Ticket was successfullly cancelled');</script>");
		    					out.print("<script>window.location.href='display_booking.jsp';</script>");
		    				
		    				}
		    			}

}
}
