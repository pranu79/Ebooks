<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
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
<%@include file="All_Components/all_css.jsp"%>
<title>E-Book : Setting</title>
<style>
body {
	background-color: #edede9;
}

.container a {
	color: black;
	text-decoration: none;
}

.container a:visited {
	color: black;
}

.container a:hover {
	color: rgb(0, 128, 255);
}
</style>
</head>
<body>
	<%@include file="All_Components/navbar.jsp"%>

	<c:if test="${empty userobj}">
		<c:redirect url="login.jsp"></c:redirect>
	</c:if>

	<div class="container text-center ">

		<c:if test="${not empty userobj}">
			<h3 class="my-2">Hello, ${userobj.name}</h3>
		</c:if>

		<div class="row p-2 mt-4">
			<div class="col-md-4">
				<a href="sellBook.jsp">
					<div class="card p-4">
						<div class="card-body">
							<i class="fas fa-book-open fs-1 text-primary"></i>
							<h3>Sell Old Book</h3>
						</div>
					</div>
				</a>
			</div>

			<div class="col-md-4 ">
				<a href="oldBooks.jsp">
					<div class="card p-4">
						<div class="card-body">
							<i class="fas fa-book-open fs-1 text-success"></i>
							<h3>Old Books</h3>
						</div>
					</div>
				</a>
			</div>

			<div class="col-md-4">
				<a href="editProfile.jsp">
					<div class="card p-2">
						<div class="card-body">
							<i class="fas fa-user-edit fs-1 text-primary"></i>
							<h3>Login & Security (Edit Profile)</h3>
						</div>
					</div>
				</a>
			</div>

		</div>
	</div>

	<div class="container text-center ">
		<div class="row p-2">
			<div class="col-md-4">
				<a href="addAddress.jsp">
					<div class="card p-3">
						<div class="card-body">
							<i class="fas fa-map-marker-alt fs-1 text-warning"></i>
							<h3>Your Address</h3>
							<p>Edit Address</p>
						</div>
					</div>
				</a>
			</div>

			<div class="col-md-4">
				<a href="orderTrack.jsp">
					<div class="card p-3">
						<div class="card-body">
							<i class="fas fa-box-open fs-1 text-danger"></i>
							<h3>My Order</h3>
							<p>Track your Order</p>
						</div>
					</div>
				</a>
			</div>

			<div class="col-md-4">
				<a href="service.jsp">
					<div class="card p-3">
						<div class="card-body">
							<i class="fas fa-user-circle fs-1 text-primary"></i>
							<h3>Help Center</h3>
							<p>24*7 Service</p>
						</div>
					</div>
				</a>
			</div>

		</div>
	</div>

	<div style="margin-top: 45px">
		<%@include file="All_Components/footer.jsp"%>
	</div>
</body>
</html>