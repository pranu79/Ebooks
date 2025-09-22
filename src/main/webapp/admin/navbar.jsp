<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<style>
/* Top Navbar */
.bg-custom {
	background-color: #004080;
	box-shadow: 0 2px 5px rgba(0,0,0,0.2);
}

.top-icons .btn {
	min-width: 120px;
	margin: 2px;
}

/* Responsive search form */
.search-form {
	/* flex: 1 1 300px;
	margin: 0 15px; */
	max-width: 400px;
	width: 100%;

}



/* Flex wrap on smaller screens */
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
	
}
@media (max-width: 576px) {
	.search-form {
		max-width: 100%;
	}
}



</style>

<!-- Top Navbar -->
<div class="container-fluid"
	style="height: 10px; background-color: #004080;"></div>
<div class="container-fluid py-2 text-light">
	<div class="d-flex flex-wrap align-items-center justify-content-between">
		<!-- Logo -->
		<div class="me-auto mb-2 mb-md-0">
			<h1 class="text-success mb-0">
				<i class="fa-solid fa-book" style="color: green;"></i> E-Book
			</h1>
		</div>

		<!-- Search Box -->
			<form class="d-flex mx-auto search-form" action="<c:url value='/admin/searchBook'/>" method="get">
				<input class="form-control" type="text" placeholder="Search Book" aria-label="Search" name="keyword" required autocomplete="off">
				<button class="btn btn-light ms-2" type="submit">Search</button>
			</form>

		<!-- Buttons -->
		<div class="d-flex top-icons flex-wrap ms-auto mb-2 mb-md-0">
			<c:if test="${not empty adminobj}">
				<a class="btn btn-success my-1" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
					<i class="fa-solid fa-user-circle me-1"></i> ${adminobj.username}
				</a>
			</c:if>

			<c:if test="${empty adminobj}">
				<a href="<c:url value='/admin/login.jsp'/>" class="btn btn-dark my-1">
					<i class="fa-solid fa-user-shield me-1"></i> Admin Panel
				</a>
				<a href="../login.jsp" class="btn btn-info my-1">
					<i class="fa-solid fa-user me-1"></i> User Sign In
				</a>
				<a href="../register.jsp" class="btn btn-warning my-1">
					<i class="fa-solid fa-user-plus me-1"></i> Register
				</a>
			</c:if>
		</div>
	</div>
</div>

<!-- Logout Modal -->
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static"
	data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Log Out</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body text-center fs-4">Are you sure you want to Logout?</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal">Close</button>
				<a href="${pageContext.request.contextPath}/AdminLogout"
					class="btn btn-primary text-light">Logout</a>
			</div>
		</div>
	</div>
</div>

<!-- Sub Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #004080; box-shadow: 0 2px 5px rgba(0,0,0,0.2);">
	<div class="container-fluid">
		

		<div class="navbar-collapse justify-content-left" id="subNavbarContent">
			<ul class="navbar-nav mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link active" href="home.jsp"><i class="fa-solid fa-house-chimney me-1"></i> Home</a></li>
			</ul>
		</div>
	</div>
</nav>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
