<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="shopping.repository.ProductRepository"%>
<%@ page import="shopping.entity.Product"%>
<%@ page import="java.util.List"%>
<%
List<Product> products = new ProductRepository().getProducts();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shopping</title>
<%@include file="includes/head.jsp"%>
</head>
<body>
	<%@include file="includes/navbar.jsp"%>

	<div class="container">
		<div class="card-header my-3">All Products</div>
		<div class="row">
			<%
			if (!products.isEmpty()) {
				for (Product p : products) {
			%>
			<div class="col-md-3 my-3">
				<div class="card w-100" style="width: 18rem;">
					<img src="product-image/<%=p.getImage()%>" height="400" class="rounded"
						alt="...">
					<div class="card-body">
						<h5 class="card-title"><%=p.getName()%></h5>
						<h6 class="price">
							Rs.
							<%=p.getPrice()%></h6>
						<h6 class="category"><%=p.getCategory()%></h6>

						<div class="m-3" >
							<a href="add-to-cart?id=<%= p.getPid() %>" class="btn btn-dark">Add to Cart</a> 
							<a href="buy-now?quantity=1&id=<%=p.getPid()%>"
								class="btn btn-primary ms-3">Buy Now</a>
						</div>
					</div>
				</div>
			</div>
			<%
			}
			}
			%>

		</div>
	</div>

	<%@include file="includes/js.jsp"%>
</body>
</html>