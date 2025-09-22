<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<%@page import="com.entity.Admin"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>E-Book: Admin Panel</title>
<%@include file="all_css.jsp"%>
<style>
.hover-col:hover {
	background-color: #edede9;
	transition: 0.3s;
}

a {
	text-decoration: none;
	color: black;
}

a:visited {
	color: black;
}

.card-icon i {
	font-size: 2.5rem;
}

.card-icon h4 {
	font-size: 1.1rem;
}

.card-icon p {
	margin: 0;
	color: #888;
}

@media (max-width: 992px) {
	.card-icon i {
		font-size: 2.2rem;
	}
	.card-icon h4 {
		font-size: 1rem;
	}
}

@media (max-width: 576px) {
	.card-icon i {
		font-size: 2rem;
	}
	.card-icon h4 {
		font-size: 0.95rem;
	}
}
</style>
</head>
<body>

<c:if test="${empty adminobj}">
	<c:redirect url="/admin/login.jsp"></c:redirect>
</c:if>
<% Admin admin = (Admin) session.getAttribute("adminobj"); %>

<%@include file="navbar.jsp"%>

<h2 class="text-center mt-3 mb-4">Welcome To Admin Page</h2>

<div class="container">
	<div class="row justify-content-center g-3">

		<div class="col-6 col-sm-4 col-md-3 col-lg-2 text-center">
			<a href="addbook.jsp">
				<div class="card hover-col h-100">
					<div class="card-body d-flex flex-column align-items-center justify-content-center">
						<div class="card-icon text-center">
							<i class="fa-solid fa-square-plus mb-2" style="color: #195ce1;"></i>
							<h4>Add Books</h4>
							<p>--------</p>
						</div>
					</div>
				</div>
			</a>
		</div>

		<div class="col-6 col-sm-4 col-md-3 col-lg-2 text-center">
			<a href="allbooks.jsp">
				<div class="card hover-col h-100">
					<div class="card-body d-flex flex-column align-items-center justify-content-center">
						<div class="card-icon text-center">
							<i class="fa-solid fa-book-open mb-2" style="color: #ec0909;"></i>
							<h4>All Books</h4>
							<p>--------</p>
						</div>
					</div>
				</div>
			</a>
		</div>

		<div class="col-6 col-sm-4 col-md-3 col-lg-2 text-center">
			<a href="order.jsp">
				<div class="card hover-col h-100">
					<div class="card-body d-flex flex-column align-items-center justify-content-center">
						<div class="card-icon text-center">
							<i class="fa-solid fa-box-open mb-2" style="color: #ddcd1d;"></i>
							<h4>Order</h4>
							<p>--------</p>
						</div>
					</div>
				</div>
			</a>
		</div>

		<div class="col-6 col-sm-4 col-md-3 col-lg-2 text-center">
			<a href="reconcile.jsp">
				<div class="card hover-col h-100">
					<div class="card-body d-flex flex-column align-items-center justify-content-center">
						<div class="card-icon text-center">
							<i class="fa-solid fa-money-check mb-2" style="color: #26822c;"></i>
							<h4>Reconcile</h4>
							<p>--------</p>
						</div>
					</div>
				</div>
			</a>
		</div>

		<div class="col-6 col-sm-4 col-md-3 col-lg-2 text-center">
			<a data-bs-toggle="modal" data-bs-target="#staticBackdrop">
				<div class="card hover-col h-100">
					<div class="card-body d-flex flex-column align-items-center justify-content-center">
						<div class="card-icon text-center">
							<i class="fa-solid fa-right-from-bracket mb-2" style="color: #165cd4;"></i>
							<h4>Logout</h4>
							<p>--------</p>
						</div>
					</div>
				</div>
			</a>
		</div>

	</div>
</div>

<div class="mt-5">
	<%@include file="footer.jsp"%>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
