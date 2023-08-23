<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="shopping.entity.Cart"%>
<%@page import="shopping.service.CartService"%>
<%
ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cartList");
List<Cart> cartProduct = null;
if (cartList != null) {
	cartProduct = new CartService().getCartProducts(cartList);
	session.setAttribute("totalPrice",new CartService().getTotalCartPrice(cartList));
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cart</title>
<%@include file="includes/head.jsp"%>
<style type="text/css">
th,td{
text-align:center;
vertical-align:middle;
}
.btn-incre, .btn-decre {
	box-shadow: none;
	font-size: 25px;
}
</style>
</head>
<body>
	<%@include file="includes/navbar.jsp"%>
	<div class="container">
		<div class="d-flex py-3">
			<h3>Total Price: Rs.<%=session.getAttribute("totalPrice") %></h3>
			<h3>
				<a class="mx-3 btn btn-primary" href="checkout">Check Out</a>
			</h3>
		</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Price</th>
					<th scope="col">Quantity</th>
					<th scope="col">Buy Now</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (cartList != null) {
					for (Cart c : cartProduct) {
				%>
				<tr>
					<td><%=c.getName()%></td>
					<td><%=c.getCategory()%></td>
					<td><%=c.getPrice()%></td>
					<td>
						<form id="form-1" action="buy-now" method="post" class="">
							<input type="hidden" name="id" value="<%=c.getPid()%>"
								class="form-input">
							<div class="form-group d-flex w-5">
								<a class="btn bnt-sm btn-incre"
									href="quantity?action=inc&id=<%=c.getPid()%>">
									<i
									class="fas fa-plus-square m-1"></i>
									</a> 
									<input type="text"
									name="quantity" class="form-control"
									value="<%=c.getQuantity()%>" readonly> <a
									class="btn btn-sm btn-decre"
									href="quantity?action=dec&id=<%=c.getPid()%>"><i
									class="fas fa-minus-square"></i></a>
							</div>
						</form>
					</td>
					<td>
					<button type="submit" form="form-1" class="btn btn-lg btn-primary">Buy Now</button>
					</td>
					<td><a class="btn btn-lg btn-danger" href="remove-item?id=<%=c.getPid()%>">Remove</a></td>
				</tr>
				<%
				}
				}
				%>

			</tbody>
		</table>
	</div>
	<%@include file="includes/js.jsp"%>
</body>
</html>