<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
if (request.getSession().getAttribute("user") != null) {
	response.sendRedirect("index.jsp");
}
%>
}
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<%@include file="includes/head.jsp"%>
</head>
<body>
	<%@include file="includes/navbar.jsp"%>
	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">User Login</div>
			<div class="card-body">
				<form action="user-login" method="post">
					<div class="form-group">
						<label>Email Address</label> <input type="email"
							class="form-control" name="login-email" placeholder="Enter Email"
							required> <label>Password</label> <input type="password"
							class="form-control" name="login-password" placeholder="*******"
							required>
						<div class="text-center">
							<button type="submit" class="btn btn-primary mt-4">Login</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="d-flex justify-content-center">
		<a href="register.jsp">Register here</a>
		</div>
		
	</div>
	
	<%@include file="includes/js.jsp"%>
</body>
</html>