<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.sql.*"
	import="javax.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>BUAir | Cancel Ticket</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css"
	integrity="sha384-r4NyP46KrjDleawBgD5tp8Y7UzmLA05oM1iAEQ17CSuDqnUK2+k9luXQOfXJCJ4I"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bs-custom-file-input/dist/bs-custom-file-input.js"></script>

<script src='https://api.mapbox.com/mapbox-gl-js/v1.12.0/mapbox-gl.js'></script>
<link href='https://api.mapbox.com/mapbox-gl-js/v1.12.0/mapbox-gl.css'
	rel='stylesheet' />
<link rel="stylesheet" href="css/app.css?version=1">


</head>

<body class="d-flex flex-column vh-100"
	style='background-image: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url("https://source.unsplash.com/ANAa-P_e2lE");'>
	<%
		HttpSession ses = request.getSession();
		if (ses.getAttribute("uname") == null) {
			response.sendRedirect("signin.jsp");
		}
	%>
	<nav class="navbar sticky-top navbar-expand-lg navbar-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">BUAir</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
				<div class="navbar-nav">
					<a class="nav-link" href="home.jsp">Home</a>
				</div>
				<div class="navbar-nav ml-auto">
					<form action="display_booking.jsp" class="mr-3">
						<button class="btn btn-dark btn-block">My Bookings</button>
					</form>
					<form action="search_flight.jsp" class="mr-3">
						<button class="btn btn-dark btn-block">Book a Flight</button>
					</form>
					<form action="Customer" method="post">
						<button class="btn btn-danger btn-block" name="s1" value="logout">Log
							Out</button>
					</form>
				</div>
			</div>
		</div>
	</nav>
	<main class="container mt-3">

		<div
			class="container d-flex justify-content-center align-items-center mt-2 mb-2">
			<div class="card text-center">
				<h1>Cancel Ticket</h1>
				<form action="Cancel" method="post">
					<div class="row mb-3 justify-content-center">
						<div class="col-2">
							<input class="form-select" id="tid" type="number" name="tid"
								placeholder="Ticket ID">
						</div>
						<div class="col-2">
							<input class="btn btn-outline-dark form-group" type="submit"
								value="Cancel Ticket">
						</div>
					</div>
				</form>
				<div class="card p-3">
					<div class="card-header center h2">My Bookings</div>
					<%
						out.print("<html>" + "<head>" + "<style>" + "table {\r\n" + "\r\n" + "  border-collapse: collapse;\r\n"
								+ "  width: 80%;\r\n" + "}\r\n" + "\r\n" + "td, th {\r\n" + "  border: 1px solid #dddddd;\r\n"
								+ "  text-align: left;\r\n" + "  padding: 8px;\r\n" + "}\r\n" + "\r\n" + "tr:nth-child(even) {\r\n"
								+ "  background-color: #dddddd;\r\n" + "}" + "#one{" + "color:green;}" + "#two{" + "color:red;}"
								+ "</style>" + "</head>" + "</html>");
					%>
					<%
						String url = "jdbc:mysql://localhost:3306/airline?useSSL=false";
						String root = "root";
						String password = "rootadmin";
						try {
							// either sql statement has issue 
							String sql = "select *, company.company , passenger.name from ticket inner join company on ticket.company=company.id inner join user on user.uname=ticket.uname inner join passenger on passenger.random=ticket.random where user.uname=? and passenger.prime=?";
							//String sql="select * from ticket inner join user on user.uname=ticket.uname where user.uname=? and passanger.prime=?";
							Class.forName("com.mysql.jdbc.Driver");
							java.sql.Connection con = DriverManager.getConnection(url, root, password);
							PreparedStatement st = con.prepareStatement(sql);
							String name = ses.getAttribute("uname").toString();
							st.setString(1, name);
							st.setInt(2, 1);
							ResultSet rs = st.executeQuery();
							int i = 0;
							while (rs.next()) {
								if (i == 0) {
									out.print("<table class='table table-hover' align='center'>" + "<tr>" + "<th>Ticket ID</th>"
											+ "<th>Name</th>" + "<th>From</th>" + "<th>Destination</th>" + "<th>Company</th>"
											+ "<th>Class</th>" + "<th>Date (YYYY/MM/DD)</th>" + "<th>Departure Time</th>"
											+ "<th>Arrival Time</th>" + "<th>Seats Booked</th>" + "</tr>"
									);
									i = 1;
								}
								out.print("<tr>" + "<td>" + rs.getInt(12) + "</td>" + "<td>" + rs.getString(21) + "</td>" + "<td>"
										+ rs.getString(2) + "</td>" + "<td>" + rs.getString(3) + "</td>" + "<td>" + rs.getString(14)
										+ "</td>" + "<td>" + rs.getString(24).substring(0, 1).toUpperCase()
										+ rs.getString(24).substring(1) + "</td>"
										+ "<td>" + rs.getString(9) + "</td>" + "<td>" + rs.getString(4) + "</td>" + "<td>"
										+ rs.getString(5) + "</td>" + "<td>" + rs.getString(10) + "</td>" + "</tr>");
							}
							if (i == 0) {
								out.print("You have no booked flights");
								out.print("<a class = 'text-muted' href='search_flight.jsp'>Book Now!</a>");
							}
							out.print("</table>");
						} catch (Exception e) {
							e.printStackTrace();
						}
					%>
				</div>
			</div>
		</div>

	</main>
	<footer class="footer py-3 mt-auto">
		<div class="container">
			<span class="text-white">&copy; BUAir 2021</span>
		</div>
	</footer>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js"
		integrity="sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/"
		crossorigin="anonymous"></script>

	<script src='js/validateForms.js'></script>
</body>

</html>
