package p1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Ticket class is implemented as a servlet with action "Ticket"
 * and adds flights to user bookings 
 */

@WebServlet("/Ticket")

public class Ticket extends HttpServlet {
	
	// setting the url for mysql server connection
	String url = "jdbc:mysql://localhost:3306/airline?useSSL=false";
	
	// root username
	String root = "root";
	
	// password to mysql connection
	String password = "rootadmin";

	
	// variables to store ticket info
	int ticket_id;
	String source;
	String destination;
	String departure_time;
	String arrival_time;
	String company;
	
	float price;
	
	String username;
	String date;
	int seats;
	int flight_id;
	int random;
	
	
	

	private static final long serialVersionUID = 1L;

	
	// Function that adds a new flight to the Ticket table 
	public boolean add_to_bookings(HttpServletRequest request, HttpServletResponse response, HttpSession ses,
			int seats, int flight_id, String from, String to, String clas, String company, String start_time,
			String end_time, String uname, String date, int random, float amount) {

		

		String day = ses.getAttribute("day").toString();

		// sql statement for company
		String sql = "select company.id from company where company.company=? ";

		// sql statement for inserting ticket into ticket table
		String sql1 = "insert into ticket(from2,to2,start_time,end_time,company,price,uname,date,seats,airplane_id,random) values(?,?,?,?,?,?,?,?,?,?,?)";

		// Declare sql2 as an empty string 
		String sql2 = "";
 
		
		// Selecting seats based on day
		
		if (day.equals("mon")) {
			sql2 = "select flight.mon_seats,flight.mon_seats_bus from flight where flight.id=?";
		} 
		
		if (day.equals("tue")) {
			sql2 = "select flight.tue_seats,flight.tue_seats_bus from flight where flight.id=?";
		}
		
		if (day.equals("wed")) {
			sql2 = "select flight.wed_seats,flight.wed_seats_bus from flight where flight.id=?";
		}
		if (day.equals("thu")) {
			sql2 = "select flight.thu_seats,flight.thu_seats_bus from flight where flight.id=?";
		}
		if (day.equals("fri")) {
			sql2 = "select flight.fri_seats,flight.fri_seats_bus from flight where flight.id=?";
		}
		if (day.equals("sat")) {
			sql2 = "select flight.sat_seats,flight.sat_seats_bus from flight where flight.id=?";
		}
		if (day.equals("sun")) {
			sql2 = "select flight.sun_seats,flight.sun_seats_bus from flight where flight.id=?";
		}

		String sql3 = null;
		
		// If the class is economy

		if (clas.equals("economy")) {

			

			if (day.equals("mon")) {
				sql3 = "update flight set mon_seats=? where flight.id=? ";
			}
			if (day.equals("tue")) {
				sql3 = "update flight set tue_seats=? where flight.id=? ";
			}
			if (day.equals("wed")) {
				sql3 = "update flight set wed_seats=? where flight.id=? ";
			}
			if (day.equals("thu")) {
				sql3 = "update flight set thu_seats=? where flight.id=? ";
			}
			if (day.equals("fri")) {
				sql3 = "update flight set fri_seats=? where flight.id=? ";
			}
			if (day.equals("sat")) {
				sql3 = "update flight set sat_seats=? where flight.id=? ";
			}
			if (day.equals("sun")) {
				sql3 = "update flight set sun_seats=? where flight.id=? ";
			}
		} else if (clas.equals("business")) {
			if (day.equals("mon")) {
				sql3 = "update flight set mon_seats_bus=? where flight.id=? ";
			}
			if (day.equals("tue")) {
				sql3 = "update flight set tue_seats_bus=? where flight.id=? ";
			}
			if (day.equals("wed")) {
				sql3 = "update flight set wed_seats_bus=? where flight.id=? ";
			}
			if (day.equals("thu")) {
				sql3 = "update flight set thu_seats_bus=? where flight.id=? ";
			}
			if (day.equals("fri")) {
				sql3 = "update flight set fri_seats_bus=? where flight.id=? ";
			}
			if (day.equals("sat")) {
				sql3 = "update flight set sat_seats_bus=? where flight.id=? ";
			}
			if (day.equals("sun")) {
				sql3 = "update flight set sun_seats_bus=? where flight.id=? ";
			}

		}

		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection connection = DriverManager.getConnection(url, root, password);

			// prepared statement for company table
			PreparedStatement st = connection.prepareStatement(sql);

			// prepared statement statement for ticket table
			PreparedStatement st1 = connection.prepareStatement(sql1);

			// prepared statement for
			PreparedStatement st2 = connection.prepareStatement(sql2);

			PreparedStatement st3 = connection.prepareStatement(sql3);
			

			st.setString(1, company);
			st2.setInt(1, flight_id);

			ResultSet rs = st.executeQuery();

			ResultSet rs2 = st2.executeQuery();

			rs.next();

			rs2.next();

			PrintWriter out = response.getWriter();

			int id = rs.getInt(1);
			int seats1 = 0;

			if (clas.equals("economy")) {

				seats1 = rs2.getInt(1);

			}

			else if (clas.equals("business")) {

				seats1 = rs2.getInt(2);

			}
			seats1 = seats1 - seats;

			// handle error of insufficient tickets
			if (seats1 < 0) {
				out.print("<script>window.alert('Ticket Cannot be generated due to unsufficient seats');</script>");
				RequestDispatcher rd = request.getRequestDispatcher("Search");
				rd.forward(request, response);

			}

			// setting the variable for ticket
			st1.setString(1, from);
			st1.setString(2, to);
			st1.setString(3, start_time);
			st1.setString(4, end_time);
			st1.setInt(5, id);
			st1.setFloat(6, amount);
			st1.setString(7, uname);
			st1.setString(8, date);
			st1.setInt(9, seats);
			st1.setInt(10, flight_id);
			st1.setInt(11, random);

			// 
			int ticket_response = st1.executeUpdate();
			

			// setting the variable
			st3.setInt(1, seats1);
			st3.setInt(2, flight_id);

			int seat_response = st3.executeUpdate();

			

			if (ticket_response > 0 && seat_response > 0) {

				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session = request.getSession();

		if (session.getAttribute("uname") == null) {
			response.sendRedirect("signin.jsp");
		}
        
		
		PrintWriter out = response.getWriter();

		int seats = Integer.parseInt(session.getAttribute("seats").toString());
		int flight_id = Integer.parseInt(session.getAttribute("id").toString());

		String from = session.getAttribute("from").toString();
		String to = session.getAttribute("to").toString();
		String clas = session.getAttribute("class").toString();
		String company = session.getAttribute("company").toString();
		String start_time = session.getAttribute("start_time").toString();
		String end_time = session.getAttribute("end_time").toString();
		String uname = session.getAttribute("uname").toString();
		String date = session.getAttribute("date").toString();

		int random = (Integer.parseInt(session.getAttribute("random").toString()));

		
		float amount = Float.parseFloat(session.getAttribute("amount_paid").toString());

		

		if (add_to_bookings(request, response, session, seats, flight_id, from, to, clas, company, start_time, end_time,
				uname, date, random, amount)) {
			out.print("<script>window.alert('Ticket generated succesfully, Check Your Bookings for ticket');</script>");
			out.print("<script>window.location.href='display_booking.jsp';</script>");

		} else {
			out.print("<script>window.alert('Ticket not generated,please book again');</script>");
			out.print("<script>window.location.href='search_flight.jsp';</script>");
		}

	}

}
