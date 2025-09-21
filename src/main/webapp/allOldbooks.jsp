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
							<a href="view_details.jsp?bookId=<%=b.getBookId()%>"
								class="btn btn-success btn-sm">View Details</a> <a href="#"
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