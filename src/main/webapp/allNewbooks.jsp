<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.db.DBConnect"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="com.dao.BookDao"%>
<%@page import="com.entity.Books"%>
<%@page import="java.util.List"%>
<%@page import="com.entity.User"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>E-Book Home:New Books</title>
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

#toast {
	min-width: 300px;
	position: fixed;
	bottom: 30px;
	left: 50%;
	margin-left: -125px;
	background: #333;
	padding: 10px;
	color: white;
	text-align: center;
	z-index: 1;
	font-size: 18px;
	visibility: hidden;
	box-shadow: 0px 0px 100px #000;
}

#toast.display {
	visibility: visible;
	animation: fadeIn 0.5s, fadeOut 0.5s 2.5s;
}

@
keyframes fadeIn {
	from {bottom: 0;
	opacity: 0;
}

to {
	bottom: 30px;
	opacity: 1;
}

}
@
keyframes fadeOut {
	from {bottom: 30px;
	opacity: 1;
}

to {
	bottom: 0;
	opacity: 0;
}
}
</style>
</head>
<body>

	<%
	User u = (User) session.getAttribute("userobj");
	%>

	<c:if test="${not empty addcart }">

		<div id="toast">${addcart}</div>

		<script type="text/javascript">
			showToast();
			function showToast() {
				var x = document.getElementById("toast");
				x.className = "display";

				setTimeout(function() {
					x.className = x.className.replace("display", "");

				}, 2000);
			}
		</script>

		<c:remove var="addcart" scope="session" />
	</c:if>


	<%@include file="All_Components/navbar.jsp"%>


	<div class="container-fluid">
		<h3 class="text-center py-2">New Books</h3>
		<div class="row">

			<%
			BookDao dao2 = new BookDao(DBConnect.getCon());
			List<Books> list2 = dao2.getAllNewBooks();
			for (Books b : list2) {
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
						
							<%
							if (u == null) {
							%>
							<a href="login.jsp" class="btn btn-danger"><i
								class="fa-solid fa-cart-shopping me-1"></i>
								Add Cart</a>
							<%
							} else {
							%>
							<a
								href="Cart?bookId=<%=b.getBookId()%>&&userId=<%=u.getId()%>&&quantity=1"
								class="btn btn-danger"><i
								class="fa-solid fa-cart-shopping me-1" ></i>
								Add Cart</a>
							<%
							}
							%>
							<a href="view_details.jsp?bookId=<%=b.getBookId()%>"
								class="btn btn-success"><i class="fa-solid fa-eye me-1"></i>
								View Details</a> <a href=""
								class="btn btn-warning"><i
								class="fa-solid fa-indian-rupee-sign me-1"></i><%=b.getPrice()%></a>
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