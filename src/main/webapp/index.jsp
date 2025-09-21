<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.db.DBConnect"%>
<%@page import="com.dao.BookDao"%>
<%@page import="com.entity.Books"%>
<%@page import="com.entity.User"%>
<%@page import="java.util.List"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="All_Components/all_css.jsp"%>
<title>E-BOOK</title>
<style>
.back-img {
	background-image:
		url('https://th.bing.com/th/id/R.a8868ecf82a46bc9e414744cf19d8d6f?rik=Ctgphx3828ameA&riu=http%3a%2f%2fwww.bloglet.com%2fgallery%2fhow-to-find-half-price-books%2fhow-to-find-half-price-books.jpg&ehk=a2HIUZHKt0qE0M9xHl%2f2kH%2flISvs8KAQP70Dd%2bRWVK8%3d&risl=&pid=ImgRaw&r=0');
	height: 50vh;
	width: 100%;
	background-repeat: no-repeat;
	background-size: cover;
	background-position: center;
}

.crd-ho {
	transition: 0.3s;
}

.crd-ho:hover {
	background-color: #edede9;
}

.card img {
	height: 200px;
	width: auto;
	max-width: 100%;
}

.btn-group-sm .btn {
	margin: 3px 2px;
}

@media ( max-width :768px) {
	.card-body p {
		font-size: 0.9rem;
	}
	.card img {
		height: 180px;
	}
}

@media ( max-width :576px) {
	.card img {
		height: 150px;
	}
	.btn-group-sm {
		display: flex;
		flex-direction: column;
		align-items: center;
	}
}
</style>
</head>
<body>
	<%@include file="All_Components/navbar.jsp"%>

	<%
	User u = (User) session.getAttribute("userobj");
	%>

	<div
		class="container-fluid back-img d-flex align-items-center justify-content-center">
		<h1 class="text-center text-light py-4">
			<i class="fa-solid fa-book"></i> E-Book Management System
		</h1>
	</div>

	<!-- Recent Books -->
	<div class="container py-4">
		<h3 class="text-center mb-4">Recent Books</h3>
		<div class="row g-3">
			<%
			BookDao dao = new BookDao(DBConnect.getCon());
			List<Books> list = dao.getRecentBooks();
			for (Books b : list) {
			%>
			<div class="col-6 col-sm-6 col-md-3 col-lg-3">
				<div class="card crd-ho h-100">
					<div class="card-body text-center d-flex flex-column">
						<img
							src="<%=request.getContextPath()%>/books/<%=b.getPhotoName()%>"
							alt="<%=b.getPhotoName()%>" >
						<p class="mb-1 fw-bold"><%=b.getBookname()%></p>
						<p class="mb-1"><%=b.getAuthor()%></p>
						<p class="mb-2">
							Category:
							<%=b.getBookCategory()%></p>

						<div
							class="btn-group btn-group-sm mt-auto d-flex justify-content-center flex-wrap">
							<%
							if (u == null) {
							%>
							<a href="login.jsp" class="btn btn-danger"><i
								class="fa-solid fa-cart-shopping me-1"></i> Add Cart</a>
							<%
							} else {
							%>
							<a
								href="Cart?bookId=<%=b.getBookId()%>&userId=<%=u.getId()%>&quantity=1"
								class="btn btn-danger"><i
								class="fa-solid fa-cart-shopping me-1"></i> Add Cart</a>
							<%
							}
							%>
							<a href="view_details.jsp?bookId=<%=b.getBookId()%>"
								class="btn btn-success"><i class="fa-solid fa-eye me-1"></i>
								View Details</a> <a href="#" class="btn btn-warning"><i
								class="fa-solid fa-indian-rupee-sign me-1"></i><%=b.getPrice()%></a>
						</div>
					</div>
				</div>
			</div>
			<%
			}
			%>
		</div>
		<div class="text-center mt-3">
			<a href="allRecentbooks.jsp" class="btn btn-primary btn-sm">View
				All</a>
		</div>
	</div>

	<!-- New Books -->
	<div class="container py-4">
		<h3 class="text-center mb-4">New Books</h3>
		<div class="row g-3">
			<%
			List<Books> list2 = dao.getNewBooks();
			for (Books b : list2) {
			%>
			<div class="col-6 col-sm-6 col-md-3 col-lg-3">
				<div class="card crd-ho h-100">
					<div class="card-body text-center d-flex flex-column">
						<img
							src="<%=request.getContextPath()%>/books/<%=b.getPhotoName()%>"
							alt="<%=b.getPhotoName()%>">
						<p class="mb-1 fw-bold"><%=b.getBookname()%></p>
						<p class="mb-1"><%=b.getAuthor()%></p>
						<p class="mb-2">
							Category:
							<%=b.getBookCategory()%></p>
						<div
							class="btn-group btn-group-sm mt-auto d-flex justify-content-center flex-wrap">
							<%
							if (u == null) {
							%>
							<a href="login.jsp" class="btn btn-danger"><i
								class="fa-solid fa-cart-shopping me-1"></i> Add Cart</a>
							<%
							} else {
							%>
							<a
								href="Cart?bookId=<%=b.getBookId()%>&userId=<%=u.getId()%>&quantity=1"
								class="btn btn-danger"><i
								class="fa-solid fa-cart-shopping me-1"></i> Add Cart</a>
							<%
							}
							%>
							<a href="view_details.jsp?bookId=<%=b.getBookId()%>"
								class="btn btn-success"><i class="fa-solid fa-eye me-1"></i>
								View Details</a> <a href="#" class="btn btn-warning"><i
								class="fa-solid fa-indian-rupee-sign me-1"></i><%=b.getPrice()%></a>
						</div>
					</div>
				</div>
			</div>
			<%
			}
			%>
		</div>
		<div class="text-center mt-3">
			<a href="allNewbooks.jsp" class="btn btn-primary btn-sm">View All</a>
		</div>
	</div>

	<!-- Old Books -->
	<div class="container py-4">
		<h3 class="text-center mb-4">Old Books</h3>
		<div class="row g-3">
			<%
			List<Books> list3 = dao.getOldBooks();
			for (Books b : list3) {
			%>
			<div class="col-6 col-sm-6 col-md-3 col-lg-3">
				<div class="card crd-ho h-100">
					<div class="card-body text-center d-flex flex-column">
						<img
							src="<%=request.getContextPath()%>/books/<%=b.getPhotoName()%>"
							alt="<%=b.getPhotoName()%>">
						alt="<%=b.getPhotoName()%>" class="mb-2">
						<p class="mb-1 fw-bold"><%=b.getBookname()%></p>
						<p class="mb-1"><%=b.getAuthor()%></p>
						<p class="mb-2">
							Category:
							<%=b.getBookCategory()%></p>
						<div
							class="btn-group btn-group-sm mt-auto d-flex justify-content-center flex-wrap">
							<a href="view_details.jsp?bookId=<%=b.getBookId()%>"
								class="btn btn-success"><i class="fa-solid fa-eye me-1"></i>
								View Details</a> <a href="#" class="btn btn-warning"><i
								class="fa-solid fa-indian-rupee-sign me-1"></i><%=b.getPrice()%></a>
						</div>
					</div>
				</div>
			</div>
			<%
			}
			%>
		</div>
		<div class="text-center mt-3 mb-5">
			<a href="allOldbooks.jsp" class="btn btn-primary btn-sm">View All</a>
		</div>
	</div>

	<div class="mt-5">
		<%@include file="All_Components/footer.jsp"%>
	</div>
</body>
</html>
