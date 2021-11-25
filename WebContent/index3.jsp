<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>BUAir | Admin Login</title>

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

<body class="d-flex flex-column vh-100" style = 'background-image: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)),
		url("https://source.unsplash.com/rf6ywHVkrlY");'>
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
					<a class="nav-link" href="main_page.jsp">Home</a>
				</div>
				<div class="navbar-nav ml-auto">
					<a class="nav-link" href="signin.jsp">Sign In</a>
					<a class="nav-link" href="signup.jsp">Sign Up</a>
				</div>
			</div>
		</div>
	</nav>
	<main class="container mt-5">

		<div class="container d-flex justify-content-center align-items-center mt-5 mb-2">
			<div class="row">
				<div class="col-md-6 offset-md-3 col-xl-4 offset-xl-4">
					<div class="card shadow" style="background-color:#EBE4E2;">
						<div class="card-body">
							<h5 class="card-title">Admin Login</h5>
							<form class="login100-form validate-form validated-form" action="Admin" method="post"
								novalidate>
								<div class="mb-3">
									<label class="form-label" for="username">Username</label> <input
										class="form-control" type="text" id="username" name="username"
										required>
								</div>
								<div class="mb-3">
									<label class="form-label" for="pass">Password</label> <input
										class="form-control" type="password" id="pass"
										name="pass" required>
								</div>
								<button class="btn btn-success btn-block" name="s1" value="signin"
								>Sign In</button>
							</form>

						</div>
					</div>
				</div>
			</div>
		</div>

	</main>
	<footer class="footer bg-dark py-3 mt-auto">
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
