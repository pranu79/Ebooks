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
			<div class="col-md-3 mb-3">
				<div class="card crd-ho">
					<div class="card-body text-center">
					<img src="<%=request.getContextPath()%>/books/<%=b.getPhotoName()%>" alt="<%=b.getPhotoName()%>"
							style="height: 200px; width: 150px;">
						
						<p><%=b.getBookname()%></p>
						<p><%=b.getAuthor()%></p>
						<p>
							Category:
							<%=b.getBookCategory()%></p>
						<div class="col">
							<%
							if (u == null) {
							%>
							<a href="login.jsp" class="btn btn-danger btn-sm "><i
								class="fa-solid fa-cart-shopping" style="color: #ffffff;"></i>
								Add Cart</a>
							<%
							} else {
							%>
							<a
								href="Cart?bookId=<%=b.getBookId()%>&&userId=<%=u.getId()%>&&quantity=1"
								class="btn btn-danger btn-sm "><i
								class="fa-solid fa-cart-shopping" style="color: #ffffff;"></i>
								Add Cart</a>
							<%
							}
							%>
							<a href="view_details.jsp?bookId=<%=b.getBookId()%>"
								class="btn btn-success btn-sm">View Details</a> <a href=""
								class="btn btn-danger btn-sm "><i
								class="fa-solid fa-indian-rupee-sign" style="color: #f7f7f7;"></i><%=b.getPrice()%></a>
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