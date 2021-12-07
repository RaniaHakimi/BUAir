package p1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * class Customer that implements Customer servlet
 */
@WebServlet("/Customer")

public class Customer extends User {
	
	// username and password from Customer
	String username;
	String password;
	
	private static final long serialVersionUID = 1L;
	
	
	
    	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		
    		// get the process from the request
    		String process = request.getParameter("s1").toString();
    		
    		// creating a new user
    		User user=new User();
    		
    		
    		// if the process is to sign up, then create an account
    		if(process.equals("signup")) {
    			
    			
    			String name=request.getParameter("name").toString();
    			
    	    	String email=request.getParameter("email").toString();
    	    	
    	    	String username=request.getParameter("username").toString();
    	    	
    	    	String pass=request.getParameter("pass").toString();
    	    	
    	    	String second_pass=request.getParameter("repeat-pass").toString();
    	    	
    	    	
    	    	// the second password does not match the first one, we want to output an error message
    	    	if(!pass.equals(second_pass)) {
    	    		
    	    		PrintWriter out=response.getWriter();
    	    		
    	    		out.print("<script>window.alert('Password does not match the second password');</script>");
					out.print("<script>window.location.href='signup.jsp';</script>");
				
    	    	}
    	    	// if the signup function returned true, then it means that the account was successfully created
    	    		if(user.signup(name,email,username,pass)) {
    					
    					PrintWriter out=response.getWriter();
    					out.print("<script>window.alert('Account was created successfully');</script>");
    					out.print("<script>window.location.href='signin.jsp';</script>");
    				
    					}
    	    		// if the signup function returned false, it means there was an issue
    				else {
    					PrintWriter out=response.getWriter();
    					out.print("<script>window.alert('The system could not create an account');</script>");
    					out.print("<script>window.location.href='signup.jsp';</script>");
    				}
    			
    		}
    		
    		// if the process is to sign in, we want to get the username and password and try to see if they match in the database
    		
    		if(process.equals("signin")) {
    			
    			username=request.getParameter("username").toString();
    			
    			
    			password=request.getParameter("pass").toString();
    			
    			
    				if(user.signin(username,password)) {
    					
    	    			HttpSession session=request.getSession();
    	    			// setting the session to the username
    	    			session.setAttribute("uname", username);
    	    
			
    	    			response.sendRedirect("display_booking.jsp");
    	    			session.setMaxInactiveInterval(600);
    	    		}
    				// if we could not sign in 
    	    		else {
    	    			PrintWriter out=response.getWriter();
    	    			out.print("<script>alert('Password incorrect, please enter correct one or signup if you dont have an account');"
    	    						+"window.location.href='signup.jsp';"
    	    					);
    	    			out.print("</script>");
    	    			
    	    			
    	    		}
    	    	
    		}
    		
    		// logout process
    		if(process.equals("logout")) {
    			HttpSession session =request.getSession();
    			if(user.signout(session)) {
    				response.sendRedirect("home.jsp");
    			}
    		}
    		
    	
    	}
    	
    	

}
