<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.db.DBConnect"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="com.dao.BookDao"%>
<%@page import="com.entity.Books"%>
<%@page import="com.entity.User"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>E-Book: View Details</title>
<%@include file="All_Components/all_css.jsp"%>
<style>
body {
	background-color: #edede9;
}

img {
	height: 250px;
	object-fit: cover;
	width: auto;
	max-width: 100%;
	margin: 0 auto;
}
</style>
</head>
<body>
	<%@include file="All_Components/navbar.jsp"%>
	<%
	User u = (User) session.getAttribute("userobj");
	%>
	<div class="container ">
		<div class="row my-5">

			<%
			int id = Integer.parseInt(request.getParameter("bookId"));
			BookDao dao = new BookDao(DBConnect.getCon());
			Books b = dao.getBookById(id);
			%>
			<div class="col-md-6 p-5 border text-center bg-white">

				<img
					src="<%=request.getContextPath()%>/viewImg?bookId=<%=b.getBookId()%>"
					alt="<%=b.getPhotoName()%>">

				<h5>
					Book Name:
					<h5 class="text-success"><%=b.getBookname()%></h5>
				</h5>
				<h5>
					Author:
					<h5 class="text-success"><%=b.getAuthor()%></h5>
				</h5>
				<h5>
					Category:
					<h5 class="text-success"><%=b.getBookCategory()%></h5>
				</h5>


			</div>
			<div class="col-md-6 p-5 border text-center bg-white">



				<h2><%=b.getBookname()%></h2>

				<%
				if ("Old".equals(b.getBookCategory()))

				{
				%>
				<h5 class="text-primary">Contact To Seller</h5>
				<h5 class="text-primary">
					<i class="fas fa-envelope"></i> Email:
					<%=b.getEmail()%></h5>

				<%
				}
				%>
				<div class="row p-3">
					<div class="col-md-4 p-2  text-danger">
						<i class="fa-solid fa-money-bill-wave fs-2"></i>
						<p>Cash On Delivery</p>
					</div>
					<div class="col-md-4 p-2  text-danger">
						<i class="fa-solid fa-rotate-left fs-2"></i>
						<p>Return Avaliable</p>
					</div>
					<div class="col-md-4 p-2 text-danger">
						<i class="fa-solid fa-truck fs-2"></i>
						<p>Free Delivery</p>
					</div>
				</div>

				<%
				if ("Old".equals(b.getBookCategory()))

				{
				%><a href="index.jsp" class="btn btn-primary me-2"><i
					class="fa-solid fa-cart-shopping " style="color: #ffffff;"></i>
					Continue Shopping</a>
				<%
				} else {
				%>

				<%
				if (u == null) {
				%>
				<a href="login.jsp" class="btn btn-danger "><i
					class="fa-solid fa-cart-shopping" style="color: #ffffff;"></i> Add
					Cart</a>
				<%
				} else {
				%>
				<a href="Cart?bookId=<%=b.getBookId()%>&&userId=<%=u.getId()%>"
					class="btn btn-danger "><i class="fa-solid fa-cart-shopping"
					style="color: #ffffff;"></i> Add Cart</a>
				<%
				}
				%>

				<%
				}
				%>


				<a href="" class="btn btn-danger "><i
					class="fa-solid fa-indian-rupee-sign" style="color: #f7f7f7;"></i>
					<%=b.getPrice()%></a>




			</div>

		</div>
	</div>
	<div class="mt-100px">
		<%@include file="All_Components/footer.jsp"%>
	</div>
</body>
</html>