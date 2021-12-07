<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.sql.*"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>BUAir | Admin Home</title>

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
	style='background-image: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url("https://source.unsplash.com/rf6ywHVkrlY");'>
	<%
		HttpSession ses = request.getSession();
		if (ses.getAttribute("uname") == null) {
			response.sendRedirect("adminlogin.jsp");
		}
	%>
	<script type="text/javascript">
		function display1() {
			document.getElementById("two").style.display = "none";
			document.getElementById("one").style.display = "block";
			document.getElementById("zero").style.display = "none";
			document.getElementById("three").style.display = "none";
		}
		function display2() {
			document.getElementById("zero").style.display = "none";
			document.getElementById("one").style.display = "none";
			document.getElementById("two").style.display = "block";
			document.getElementById("three").style.display = "none";
		}
		function display3() {
			document.getElementById("zero").style.display = "block";
			document.getElementById("one").style.display = "none";
			document.getElementById("two").style.display = "none";
			document.getElementById("three").style.display = "none";
		}
	</script>

	<nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">BUAir</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
				<div class="navbar-nav">
					<a class="nav-link" href="#one" onclick="display1()">Add Flight</a>
					<a class="nav-link" href="#two" onclick="display2()">Remove
						Flight</a> <a class="nav-link" href="#zero" onclick="display3()">Remove
						User</a>
				</div>
				<div class="navbar-nav ml-auto">
					<form action="Customer" method="post">
						<button class="btn btn-danger btn-block" name="s1" value="logout">Log
							Out</button>
					</form>
				</div>
			</div>
		</div>
	</nav>
	<main class="container mt-3">

		<div id="one" style="display: none">
			<div
				class="container d-flex justify-content-center align-items-center mt-2 mb-2">
				<div class="card text-center p-3" style="background-color: #EBE4E2;">
					<div class="card-header center h2 bg-white">Add Flight</div>
					<form action="Admin" method="post">
						<div class="row mt-3">
							<div class="col">
								<label for="rid">Enter Route Details</label><select
									class="form-select" id="rid" name="rid" required>
									<option value="None">None</option>

									<%
										// url connection to database
										String url = "jdbc:mysql://localhost:3306/airline?useSSL=false";
										// username for connection to database
										String root = "root";
										// password for connection to database
										String password = "rootadmin";
										Class.forName("com.mysql.jdbc.Driver");
										java.sql.Connection connection = DriverManager.getConnection(url, root, password);
										Statement st = connection.createStatement();
										// change sql statement for the routes
										String sql_statement = "select route.id,route.from1,route.to1 from route ";
										ResultSet result = st.executeQuery(sql_statement);
										while (result.next()) {
									%>
									<option value=<%=result.getInt(1)%>>
										<%=result.getString(2)%> ->
										<%=result.getString(3)%>
									</option>
									<%
										}
									%>
								</select>
							</div>
							<div class="col">
								<label for="start">Departure Time</label> <input
									class="form-select" id="start" type="text" name="start">
							</div>
							<div class="col">
								<label for="end">Arrival Time</label> <input id="end"
									class="form-select" type="text" name="end">
							</div>
						</div>
						<hr>
						<div class="row">
							<div class="col">
								<label for="cid">Enter Company Name</label> <select
									class="form-select" id="cid" name="cid" required>
									<option value="None">None</option>
									<%
										Class.forName("com.mysql.jdbc.Driver");
										// create connection object
										connection = DriverManager.getConnection(url, root, password);
										st = connection.createStatement();
										sql_statement = "select company.id,company.company from company ";
										result = st.executeQuery(sql_statement);
										while (result.next()) {
									%>
									<option value=<%=result.getInt(1)%>>
										<%=result.getString(2)%>
									</option>
									<%
										}
									%>
								</select>
							</div>
							<div class="col">
								<label for="price1">Enter Economy Price</label> <input
									id="price1" class="form-select" type="number" name="price1">
							</div>
							<div class="col">
								<label for="price2">Enter Business Price</label> <input
									id="price2" class="form-select" type="number" name="price2">
							</div>

						</div>
						<hr>
						<div class="row" style="display: none">
							<div class="col">
								<label for="dom">Enter Type</label> <select class="form-select"
									id="dom" name="dom">
									<option selected="selected" value="0">International</option>
									<option value="1">Domestic</option>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col">
								<label for="mon">Monday Availability</label>
							</div>
							<div class="col">
								<select class="form-select" id="mon" name="mon">
									<option value="1">available</option>
									<option value="0">unavailable</option>
								</select>
							</div>
							<div class="col">
								<label for="mon_seats">Number of Economy Seats</label>
							</div>
							<div class="col-1">
								<input class="form-control form-control-sm" id="mon_seats"
									type="number" name="mon_seats">
							</div>
							<div class="col">
								<label for="mon_seats_eco">Number of Business Seats</label>
							</div>
							<div class="col-1">
								<input class="form-control form-control-sm" id="mon_seats_eco"
									type="number" name="mon_seats_eco">
							</div>
						</div>
						<hr>
						<div class="row">
							<div class="col">
								<label for="tue">Tuesday Availability</label>
							</div>
							<div class="col">
								<select class="form-select" id="tue" name="tue">
									<option value="1">available</option>
									<option value="0">unavailable</option>
								</select>
							</div>
							<div class="col">
								<label for="tue_seats">Number of Economy Seats</label>
							</div>
							<div class="col-1">
								<input class="form-control form-control-sm" id="tue_seats"
									type="number" name="tue_seats">
							</div>
							<div class="col">
								<label for="tue_seats_eco">Number of Business Seats</label>
							</div>
							<div class="col-1">
								<input class="form-control form-control-sm" id="tue_seats_eco"
									type="number" name="tue_seats_eco">
							</div>
						</div>
						<hr>
						<div class="row">
							<div class="col">
								<label for="wed">Wednesday Availability</label>
							</div>
							<div class="col">
								<select class="form-select" id="wed" name="wed">
									<option value="1">available</option>
									<option value="0">unavailable</option>
								</select>
							</div>
							<div class="col">
								<label for="wed_seats">Number of Economy Seats</label>
							</div>
							<div class="col-1">
								<input class="form-control form-control-sm" id="wed_seats"
									type="number" name="wed_seats">
							</div>
							<div class="col">
								<label for="wed_seats_eco">Number of Business Seats</label>
							</div>
							<div class="col-1">
								<input class="form-control form-control-sm" id="wed_seats_eco"
									type="number" name="wed_seats_eco">
							</div>
						</div>
						<hr>
						<div class="row">
							<div class="col">
								<label for="thu">Thursday Availability</label>
							</div>
							<div class="col">
								<select class="form-select" id="thu" name="thu">
									<option value="1">available</option>
									<option value="0">unavailable</option>
								</select>
							</div>
							<div class="col">
								<label for="thu_seats">Number of Economy Seats</label>
							</div>
							<div class="col-1">
								<input class="form-control form-control-sm" id="thu_seats"
									type="number" name="thu_seats">
							</div>
							<div class="col">
								<label for="thu_seats_eco">Number of Business Seats</label>
							</div>
							<div class="col-1">
								<input class="form-control form-control-sm" id="thu_seats_eco"
									type="number" name="thu_seats_eco">
							</div>
						</div>
						<hr>
						<div class="row">
							<div class="col">
								<label for="fri">Friday Availability</label>
							</div>
							<div class="col">
								<select class="form-select" id="fri" name="fri">
									<option value="1">available</option>
									<option value="0">unavailable</option>
								</select>
							</div>
							<div class="col">
								<label for="fri_seats">Number of Economy Seats</label>
							</div>
							<div class="col-1">
								<input class="form-control form-control-sm" id="fri_seats"
									type="number" name="fri_seats">
							</div>
							<div class="col">
								<label for="fri_seats_eco">Number of Business Seats</label>
							</div>
							<div class="col-1">
								<input class="form-control form-control-sm" id="fri_seats_eco"
									type="number" name="fri_seats_eco">
							</div>
						</div>
						<hr>
						<div class="row">
							<div class="col">
								<label for="sat">Saturday Availability</label>
							</div>
							<div class="col">
								<select class="form-select" id="sat" name="sat">
									<option value="1">available</option>
									<option value="0">unavailable</option>
								</select>
							</div>
							<div class="col">
								<label for="sat_seats">Number of Economy Seats</label>
							</div>
							<div class="col-1">
								<input class="form-control form-control-sm" id="sat_seats"
									type="number" name="sat_seats">
							</div>
							<div class="col">
								<label for="sat_seats_eco">Number of Business Seats</label>
							</div>
							<div class="col-1">
								<input class="form-control form-control-sm" id="sat_seats_eco"
									type="number" name="sat_seats_eco">
							</div>
						</div>
						<hr>
						<div class="row">
							<div class="col">
								<label for="sun">Sunday Availability</label>
							</div>
							<div class="col">
								<select class="form-select" id="sun" name="sun">
									<option value="1">available</option>
									<option value="0">unavailable</option>
								</select>
							</div>
							<div class="col">
								<label for="sun_seats">Number of Economy Seats</label>
							</div>
							<div class="col-1">
								<input class="form-control form-control-sm" id="sun_seats"
									type="number" name="sun_seats">
							</div>
							<div class="col">
								<label for="sun_seats_eco">Number of Business Seats</label>
							</div>
							<div class="col-1">
								<input class="form-control form-control-sm" id="sun_seats_eco"
									type="number" name="sun_seats_eco">
							</div>
						</div>
						<hr>
						<input class="mt-2 btn btn-success" type="submit"
							value="Add Flight" name="s1">
					</form>
				</div>
			</div>

		</div>
		<div id="two" style="display: none">
			<div
				class="container d-flex justify-content-center align-items-center mt-2 mb-2">
				<div class="card text-center p-3" style="background-color: #EBE4E2;">
					<div class="card-header center h2 bg-white">Remove Flight</div>
					<form action="Admin" method="post">
						<label for="vis">Enter Flight Details:</label> <select
							class="mt-3 form-select" id="vis" name="fid" required>
							<option value="None">None</option>
							<%
								Class.forName("com.mysql.jdbc.Driver");
								// set new connection
								connection = DriverManager.getConnection(url, root, password);
								st = connection.createStatement();
								sql_statement = "select flight.id,company.company,route.from1,route.to1,flight.start_time,flight.dest_time from flight inner join route on flight.route_id=route.id inner join company on flight.company_id=company.id";
								result = st.executeQuery(sql_statement);
								while (result.next()) {
							%>
							<option value=<%=result.getInt(1)%>>
								<%=result.getString(1)%> :
								<%=result.getString(2)%> , (<%=result.getString(3)%> -
								<%=result.getString(4)%>) , (<%=result.getString(5)%> -
								<%=result.getString(6)%>)
							</option>
							<%
								}
							%>

						</select> <br> <input class="mt-3 btn btn-success" type="submit"
							value="Remove Flight" name="s1">
					</form>
				</div>
			</div>
		</div>
		<div id="zero" style="display: none">
			<div
				class="container d-flex justify-content-center align-items-center mt-2 mb-2">
				<div class="card text-center p-3" style="background-color: #EBE4E2;">
					<div class="card-header center h2 bg-white">Remove User</div>
					<form action="Admin" method="post">
						<label for="vis">Enter User ID:</label> <select
							class="mt-3 form-select" id="vis" name="uid" required>
							<option value="None">None</option>
							<%
								Class.forName("com.mysql.jdbc.Driver");
								// create connection
								connection = DriverManager.getConnection(url, root, password);
								st = connection.createStatement();
								sql_statement = "select user.id,user.name from user";
								result = st.executeQuery(sql_statement);
								while (result.next()) {
							%>
							<option value=<%=result.getInt(1)%>>
								<%=result.getString(1)%> :
								<%=result.getString(2)%>
							</option>
							<%
								}
							%>
							
						</select> <br> <input class="mt-3 btn btn-success" type="submit"
							value="Remove User" name="s1">
					</form>
				</div>
			</div>
		</div>

		<div id="three">
			<div class="text-center">
				<h1 class="text-white">BUAir Admin Home</h1>
				<p class="lead text-white">
					Use the navigation bar at the top to add flights, <br> remove
					flights, and remove users.
				</p>
			</div>
		</div>

	</main>
	<footer class="footer py-3 mt-auto bg-dark">
		<div class="container">
			<span class="text-muted">&copy; BUAir 2021</span>
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
