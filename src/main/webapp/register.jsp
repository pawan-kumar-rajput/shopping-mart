<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<%@include file="includes/head.jsp"%>
</head>
<body>
	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">User Login</div>
			<div class="card-body">
				<form action="user-register" method="post">
					<div class="form-group">
					<label>Name</label> <input type="text"
							class="form-control" name="name" placeholder="Enter Name"
							required>
						<label>Email Address</label> <input type="email"
							class="form-control" name="email" placeholder="Enter Email"
							required> <label>Password</label> <input type="password"
							class="form-control" name="password" placeholder="*******"
							required>
						<div class="text-center">
							<button type="submit" class="btn btn-primary mt-4">Register</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@include file="includes/js.jsp"%>
</body>
</html>