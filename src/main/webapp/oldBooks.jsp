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
<title>Setting: Old Books</title>
<%@include file="All_Components/all_css.jsp"%>
<style>
body {
	background-color: #edede9;
}
</style>
</head>
<body>
	<%@include file="All_Components/navbar.jsp"%>
	<c:if test="${empty userobj}">
		<c:redirect url="login.jsp"></c:redirect>
	</c:if>

	<c:if test="${not empty succMsg }">
		<div class="alert alert-success" role="alert">${succMsg}</div>
		<c:remove var="succMsg" scope="session" />
	</c:if>

	<c:if test="${not empty errorMsg }">
		<div class="alert alert-danger" role="alert">${errorMsg}</div>
		<c:remove var="errorMsg" scope="session" />
	</c:if>

	<div class="container p-4 text-center">
		<h1>Old Books</h1>

		<table class="table table-stripted mt-4 table-light">
			<thead class="">
				<tr>
					<th scope="col">Book Name</th>
					<th scope="col">Author</th>
					<th scope="col">Price</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody>
				<%
				User u = (User) session.getAttribute("userobj");

				BookDao dao = new BookDao(DBConnect.getCon());
				List<Books> list = dao.getUserOldBooks(u.getEmail(), "Old");
				for (Books b : list) {
				%>


				<tr>
					<td scope="row"><%=b.getBookname()%></td>
					<td><%=b.getAuthor()%></td>
					<td><%=b.getPrice()%></td>
					<td><a
						href="deleteOldbook?bookId=<%=b.getBookId()%>&&email=
						<%=b.getEmail()%>"
						class="btn btn-danger">Delete</a></td>

				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>


	<div style="margin-top: 45px">
		<%@include file="All_Components/footer.jsp"%>
	</div>
</body>
</html>