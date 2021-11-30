<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.sql.*"
	import="javax.servlet.http.HttpSession" import="java.io.*"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>BUAir | Add Passengers</title>

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
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");

		HttpSession ses = request.getSession();

		int i = Integer.parseInt(ses.getAttribute("i").toString());

		String clas = ses.getAttribute("class").toString();

		int seats = 0;
		int j;
		int random = (int) (Math.random() * 1000 + 1);

		if (i == 0) {
			String num_seats = request.getParameter("num").toString();
			String ticketID = request.getParameter("id").toString();
			if (num_seats == "" || ticketID == "") {
				out.print("<script>window.alert('Please fill in all fields');</script>");
				out.print("<script>window.location.href='search_flight.jsp';</script>");
			} else {
				seats = Integer.parseInt(num_seats);
				int id = Integer.parseInt(ticketID);
				ses.setAttribute("id", id);
				ses.setAttribute("seats", seats);
				j = 0;
				ses.setAttribute("j", j);
				ses.setAttribute("random", random);
			}
		} else if (i > 0) {
			seats = Integer.parseInt(ses.getAttribute("seats").toString());
			i++;
			ses.setAttribute("i", i);

		}
		int prime = 1;
		int nonprime = 0;
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

		<%
			if (i == 0) {
		%>
		<div class="row">
			<div
				class="col-8 container d-flex justify-content-center align-items-center mt-2 mb-2">
				<div class="card text-center p-3">
					<div class="card-header center h2">Enter Primary Passenger</div>
					<form action='Add1' method='post' id="one">
						Name: <input class="form-select" type='text' name='pname' required>
						Age: <input class="form-select" type='number' name='age' required>
						Gender: <select class="form-select" name="gender" required>
							<option value='Male'>Male</option>
							<option value='Female'>Female</option>
						</select> Class: <br>
						<p
							style='border: 0.5px; border-radius: 5px; border-style: solid; border-color: LightGray; padding: 0.5em;'><%=clas.substring(0, 1).toUpperCase() + clas.substring(1)%>
						</p>
						<%
							i++;
								ses.setAttribute("i", i);
						%>
						<br> <input class="btn btn-outline-dark" type='submit'
							value='add' name='add'>
					</form>
				</div>
			</div>
			<%
				}
			%>

			<%
				j = Integer.parseInt(ses.getAttribute("j").toString());
				i = Integer.parseInt(ses.getAttribute("i").toString());
				if (j < seats - 1 && i > 1) {
			%>
			<div class="row">
				<div
					class="col-8 container d-flex justify-content-center align-items-center mt-2 mb-2">
					<div class="card text-center p-3">
						<div class="card-header center h2">
							Enter Passenger
							<%=j + 1%>
						</div>
						<form action='Add1' method='post' id="two">
							Name: <input class="form-select" type='text' name='pname'
								required> Age: <input class="form-select" type='number'
								name='age' required> Gender: <select class="form-select"
								name="gender" required>
								<option value='Male'>Male</option>
								<option value='Female'>Female</option>
							</select> Class: <br>
							<p
								style='border: 0.5px; border-radius: 5px; border-style: solid; border-color: LightGray; padding: 0.5em;'><%=clas.substring(0, 1).toUpperCase() + clas.substring(1)%>
							</p>
							<%
								j++;
									i++;
									ses.setAttribute("j", j);
									ses.setAttribute("i", i);
							%>
							<br> <input class="btn btn-outline-dark" type='submit'
								value='Add' name='add'>
						</form>
					</div>
				</div>
				<%
					}
				%>
				<%
					if (j >= seats - 1) {
						out.print("<script>document.getElementById('two')='none'</script>");
				%>
				<div
					class="col-4 container d-flex justify-content-center align-items-center mt-2 mb-2">
					<div class="card text-center p-3">
						<div class="card-header center h2">Generate Ticket!</div>
						<h3>Do not submit before adding passenger details</h3>
						<br>
						<form action="Book" method="post">
							<input class="btn btn-outline-dark" type="submit" value="Proceed">
						</form>
					</div>
				</div>
			</div>
		</div>
		<%
			}
		%>

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
