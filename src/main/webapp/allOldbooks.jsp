<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.db.DBConnect"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="com.dao.BookDao"%>
<%@page import="com.entity.Books"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>E-Book Home:Old Books</title>
<%@include file="All_Components/all_css.jsp"%>
<style>
.crd-ho:hover {
	background-color: #edede9;
}

.crd-ho {
	transition: 0.3s;
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

	<div class="container-fluid">
		<h3 class="text-center py-2">Old Books</h3>
		<div class="row">

			<%
			BookDao dao3 = new BookDao(DBConnect.getCon());
			List<Books> list3 = dao3.getAllOldBooks();
			for (Books b : list3) {
			%>

			<div class="col-6 col-sm-6 col-md-3 col-lg-3">
				<div class="card crd-ho h-100">
					<div class="card-body text-center d-flex flex-column">

						<img
							src="<%=request.getContextPath()%>/viewImg?bookId=<%=b.getBookId()%>"
							alt="<%=b.getPhotoName()%>">

						<p class="mb-1 fw-bold"><%=b.getBookname()%></p>
						<p class="mb-1"><%=b.getAuthor()%></p>
						<p class="mb-2">
							Category:
							<%=b.getBookCategory()%></p>
						<div class="btn-group btn-group-sm mt-auto d-flex justify-content-center flex-wrap">
							<a href="view_details.jsp?bookId=<%=b.getBookId()%>"
								class="btn btn-success"><i class="fa-solid fa-eye me-1"></i>
								View Details</a> <a href="#" class="btn btn-warning"><i
								class="fa-solid fa-indian-rupee-sign me-1"
								></i><%=b.getPrice()%></a>
						</div>
					</div>
				</div>
			</div>
			<%
			}
			%>

		</div>

		<div class="mt-100px">
			<%@include file="All_Components/footer.jsp"%>
		</div>
</body>
</html>