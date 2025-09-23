<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<style>
/* Common Styles */
.cart:hover {
	color: black;
}

.cart {
	position: relative;
	top: 5px;
}

.error {
	color: red;
}

.bg-custom {
	background-color: #004080;
	box-shadow: 0 2px 5px rgba(0,0,0,0.2);
}

.search-form {
	max-width: 400px;
	width: 100%;
}

.top-icons .btn {
	min-width: 120px;
}

/* Responsive */
@media (max-width: 992px) {
	.search-form {
		max-width: 300px;
		margin: 10px auto;
	}
	.top-icons {
		width: 100%;
		justify-content: center;
		margin-top: 10px;
		flex-wrap: wrap;
	}
	.top-icons .btn, .top-icons .cart {
		margin: 5px 2px;
	}
}
@media (max-width: 576px) {
	.search-form {
		max-width: 100%;
	}
	.navbar-nav {
		text-align: center;
	}
}
</style>

<!-- Top Navbar -->
<div class="container-fluid"
	style="height: 10px; background-color: #004080;"></div>
<div class="container-fluid py-2  text-light">
	<div class="d-flex flex-wrap align-items-center justify-content-between">
		<!-- Logo -->
		<div class="me-auto">
			<h1 class="text-success mb-0">
				<i class="fa-solid fa-book"></i> E-Book
			</h1>
		</div>

		<!-- Search Box -->
		<form class="d-flex mx-auto search-form" action="search.jsp" method="post">
			<input class="form-control" type="search" placeholder="Search Book" aria-label="Search" name="ch" required>
			<button class="btn btn-light ms-2" type="submit">Search</button>
		</form>

		<!-- User/Admin/Register Buttons -->
		<div class="d-flex top-icons flex-wrap">
			<c:if test="${not empty userobj }">
				<a href="checkout.jsp" class="fs-5 text-decoration-none cart mx-1 my-1">
					<i class="fa-solid fa-cart-plus fs-5"></i> Cart
				</a>
				<a href="logout" class="btn btn-success mx-1 my-1">
					<i class="fa-solid fa-user-circle me-1"></i> ${userobj.name}
				</a>
				<a href="logout" class="btn btn-primary mx-1 my-1">
					<i class="fa-solid fa-sign-out-alt me-1"></i> Logout
				</a>
			</c:if>

			<c:if test="${empty userobj}">
				<a href="admin/login.jsp" class="btn btn-dark mx-1 my-1">
					<i class="fa-solid fa-user-shield me-1"></i> Admin Panel
				</a>
				<a href="login.jsp" class="btn btn-info mx-1 my-1">
					<i class="fa-solid fa-user me-1"></i> User Sign In
				</a>
				<a href="register.jsp" class="btn btn-warning mx-1 my-1">
					<i class="fa-solid fa-user-plus me-1"></i> Register
				</a>
			</c:if>
		</div>
	</div>
</div>

<!-- Sub Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark" style="background-color:#004080; box-shadow:0 2px 5px rgba(0,0,0,0.2);">
	<div class="container-fluid">
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" 
		        data-bs-target="#subNavbarContent" aria-controls="subNavbarContent" 
		        aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="subNavbarContent">
			<ul class="navbar-nav mx-auto mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link active" href="index.jsp"><i class="fa-solid fa-house-chimney me-1"></i> Home</a></li>
				<li class="nav-item"><a class="nav-link" href="allRecentbooks.jsp"><i class="fa-solid fa-book-open me-1"></i> Recent Book</a></li>
				<li class="nav-item"><a class="nav-link" href="allNewbooks.jsp"><i class="fa-solid fa-book-open me-1"></i> New Book</a></li>
				<li class="nav-item"><a class="nav-link" href="allOldbooks.jsp"><i class="fa-solid fa-book me-1"></i> Old Book</a></li>
			</ul>
			<a class="btn btn-light mx-1 my-1" href="setting.jsp"><i class="fa-solid fa-user-gear me-1"></i> Settings</a>
		</div>
	</div>
</nav>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
