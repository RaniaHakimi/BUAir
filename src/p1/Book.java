package p1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Book")
public class Book extends HttpServlet {
	
	// Our Book class has attributes for departing city, destination city,
	// number of seats, the tax, and the total ticket price
	// Our id is our primary key in the database
	
	private String departing_city;
	private String destination_city; 
	private String class_flight;
	private int seats, id;
	private float price, tax;
	
	
	// Connect to our database
	String url = "jdbc:mysql://localhost:3306/airline?useSSL=false";
	String root = "root";
	String password = "rootadmin";

	private static final long serialVersionUID = 1L;

	/**
	 * book_ticket processes the number of passengers, the class, and the price to
	 * generate a plane ticket and display it to the front-end
	 * 
	 * @param ses            our HTTP session; it checks if the user is logged in
	 * @param departing_city our departing city, submitted from our Search.java form
	 * @param to             our destination city, submitted from our Search.java
	 *                       form
	 * @param clas           our class (economy/business), submitted from
	 *                       Search.java
	 * @param id             our ticket id that can be later used for canceling a
	 *                       ticket
	 * @param seats          the number of passengers, which affects our total
	 *                       ticket price
	 */
	public void book_ticket(HttpSession ses, HttpServletRequest request, HttpServletResponse response,
			String departing_city, String to, String clas, int id, int seats) throws IOException {
		ses.setAttribute("seats", seats);
		PrintWriter out = response.getWriter();
		
		// Create MySQL statement to retrieve all needed values
		String sql = "select company.company,flight.start_time,flight.dest_time,flight.price,flight.price2 from flight inner join company on company.id=flight.company_id inner join route on route.id=flight.route_id where flight.id=?";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, root, password);
			PreparedStatement st = connection.prepareStatement(sql);
			// set an ID
			st.setInt(1, id);
			// execute the Query and save the result as rs
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				// Create a new format for rounding to two decimal places
				DecimalFormat df = new DecimalFormat();
				df.setMaximumFractionDigits(2);
				// set total to be equal to the economy price
				float total = ((float) seats * rs.getFloat(4));
				if (clas.equals("business"))
					// change to business price if the class is business
					total = ((float) seats * rs.getFloat(5));
				// calculate a 10% tax
				tax = (float) ((0.10) * total);
				price = total + tax;
				// use session to set the rest of the attributes for our ticket
				ses.setAttribute("company", rs.getString(1));
				ses.setAttribute("start_time", rs.getString(2));
				ses.setAttribute("end_time", rs.getString(3));
				ses.setAttribute("amount_paid", price);
				ses.setAttribute("id", id);
				// Front-end: link bootstrap and custom table styles
				out.print("<html>" + "<head><link rel=\"stylesheet\"\r\n"
						+ "	href=\"https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css\"\r\n"
						+ "	integrity=\"sha384-r4NyP46KrjDleawBgD5tp8Y7UzmLA05oM1iAEQ17CSuDqnUK2+k9luXQOfXJCJ4I\"\r\n"
						+ "	crossorigin=\"anonymous\">\r\n" + "<script\r\n"
						+ "	src=\"https://cdn.jsdelivr.net/npm/bs-custom-file-input/dist/bs-custom-file-input.js\"></script>\r\n"
						+ "<script src='https://api.mapbox.com/mapbox-gl-js/v1.12.0/mapbox-gl.js'></script>\r\n"
						+ "<link href='https://api.mapbox.com/mapbox-gl-js/v1.12.0/mapbox-gl.css'\r\n"
						+ "	rel='stylesheet' />\r\n" + "<link rel=\"stylesheet\" href=\"css/app.css?version=1\">\r\n"
						+ "<style>" + "table {\r\n" + "  font-family: arial, sans-serif;\r\n"
						+ "  border-collapse: collapse;\r\n" + "  width: 80%;\r\n" + "}\r\n" + "\r\n" + "td, th {\r\n"
						+ "  border: 1px solid #dddddd;\r\n" + "  text-align: left;\r\n" + "  padding: 8px;\r\n"
						+ "}\r\n" + "\r\n" + "tr:nth-child(even) {\r\n" + "  background-color: #dddddd;\r\n" + "}"
						+ "#one{" + "color:green;}" + "#two{" + "color:red;}" + "</style>" + "</head>" + "</html>");
				out.print("<html>");
				// Load in image and body styles
				out.print(
						"<body class=\"d-flex flex-column vh-100\" style = 'background-image: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)),\r\n"
								+ "		url(\"https://source.unsplash.com/ANAa-P_e2lE\");'>");
				// Load in navigation bar
				out.print("<nav class=\"navbar sticky-top navbar-expand-lg navbar-dark bg-dark\">\r\n"
						+ "		<div class=\"container-fluid\">\r\n"
						+ "			<a class=\"navbar-brand\" href=\"#\">BUAir</a>\r\n"
						+ "			<button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\"\r\n"
						+ "				data-target=\"#navbarNavAltMarkup\" aria-controls=\"navbarNavAltMarkup\"\r\n"
						+ "				aria-expanded=\"false\" aria-label=\"Toggle navigation\">\r\n"
						+ "				<span class=\"navbar-toggler-icon\"></span>\r\n" + "			</button>\r\n"
						+ "			<div class=\"collapse navbar-collapse\" id=\"navbarNavAltMarkup\">\r\n"
						+ "				<div class=\"navbar-nav\">\r\n"
						+ "					<a class=\"nav-link\" href=\"home.jsp\">Home</a>\r\n"
						+ "				</div>\r\n" + "<div class=\"navbar-nav ml-auto\">\r\n"
						+ "					<form action=\"Customer\" method=\"post\">\r\n"
						+ "						<button class=\"btn btn-danger btn-block\" name=\"s1\" value=\"logout\">Log\r\n"
						+ "							Out</button>\r\n" + "					</form>\r\n"
						+ "				</div>\r\n" + "			</div>\r\n" + "		</div>\r\n" + "	</nav>");
				// Create all the bootstrap classes to create a card as our main content
				out.print("<main class=\"container mt-4 mb-4\">\r\n" + "\r\n" + "		<div\r\n"
						+ "			class=\"container d-flex justify-content-center align-items-center mt-2 mb-2\">\r\n"
						+ "			<div class=\"card text-center\">\r\n" + "				<div class=\"card p-3\">");
				out.print("<div class='card-header center h2'>Ticket Information</div>");
				out.print("<table align='center'><tr>");
				out.print("<tr><td>Cost of 1 Seat:</td>");
				if (clas.equals("economy")) {
					out.print("<td>" + "$" + df.format(rs.getFloat(4)) + "</td></br></tr>");
					out.print(
							"<tr><td>Total Amount: (" + (int) seats + "*" + df.format(rs.getFloat(4)) + ")" + "</td>");
				} else if (clas.equals("business")) {
					out.print("<td>" + "$" + df.format(rs.getFloat(5)) + "</td></br></tr>");
					out.print(
							"<tr><td>Total Cost:(  " + (int) seats + " * " + df.format(rs.getFloat(5)) + ")" + "</td>");
				}
				out.print("<td>" + "$" + df.format(total) + "</td></br></tr>");
				out.print("<tr><td>Total Tax:</td>");
				out.print("<td>" + "$" + df.format(tax) + "</td></br></tr>");
				out.print("<tr><td>Final Price:</td>");
				out.print("<td>" + "$" + df.format(price) + "</td></br></tr>");
				out.print("<tr>");
				out.print("<form action='Ticket' method='post'>");
				// Our submit button to generate the ticket
				out.print(
						"<input class='btn btn-success mb-4' type='submit' value='Generate Ticket'></tr></form></table");
				out.print("</div> </div> </div> </main> </body></html>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		HttpSession session = request.getSession();
		session = request.getSession();
		// check if user is logged in, if not, redirect to sign in page
		if (session.getAttribute("uname") == null) {
			response.sendRedirect("signin.jsp");
		}
		// initialize all our needed information for the ticket
		departing_city = session.getAttribute("from").toString();
		destination_city = session.getAttribute("to").toString();
		class_flight = session.getAttribute("class").toString();
		id = Integer.parseInt(session.getAttribute("id").toString());
		seats = Integer.parseInt(session.getAttribute("seats").toString());
		
		// main function call that calculates the price and displays the front-end
		book_ticket(session, request, response, departing_city, destination_city, class_flight, id, seats);
	}

}