<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<%@page import="com.db.DBConnect"%>
<%@page import="com.dao.BookDao"%>
<%@page import="com.entity.Books"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin: All Books</title>
<%@include file="all_css.jsp"%>
<style>
body {
	background-color: #edede9;
}


.table img {
	height: 150px;
	width:100px;
	object-fit: cover;
	margin:0 auto;
}

@media ( max-width : 768px) {
	.table th, .table td {
		font-size: 0.85rem;
		padding: 0.35rem;
	}
	.btn {
		font-size: 0.75rem;
		padding: 0.25rem 0.5rem;
		margin-bottom: 2px;
	}
}
</style>
</head>
<body>
	<%@include file="navbar.jsp"%>

	<c:if test="${empty adminobj}">
		<c:redirect url="/admin/login.jsp"></c:redirect>
	</c:if>

	<h2 class="text-center mt-2 mb-3">All Books</h2>

	<!-- ✅ Messages -->
	<c:if test="${not empty succMsg}">
		<p class="text-center text-success fs-5">${succMsg}</p>
		<c:remove var="succMsg" scope="session" />
	</c:if>
	<c:if test="${not empty errorMsg}">
		<p class="text-center text-danger fs-5">${errorMsg}</p>
		<c:remove var="errorMsg" scope="session" />
	</c:if>

	<div class="container-fluid">
		<div class="row">
			<div class="col-12">

				<div class="table-responsive">
					<table class="table table-striped table-bordered">
						<thead class="bg-dark text-light">
							<tr>
								<th scope="col">Id</th>
								<th scope="col">Book Image</th>
								<th scope="col">Book Name</th>
								<th scope="col">Author Name</th>
								<th scope="col">Price</th>
								<th scope="col">Book Categories</th>
								<th scope="col">Book Status</th>
								<th scope="col">Action</th>
							</tr>
						</thead>
						<tbody>
							<%
							List<Books> booklist = (List<Books>) request.getAttribute("bookList");
							if (booklist == null) {
								BookDao dao = new BookDao(DBConnect.getCon());
								booklist = dao.getAllBooks();
							}
							if (booklist != null && !booklist.isEmpty()) {
								for (Books b : booklist) {
							%>
							<tr>
								<th scope="row"><%=b.getBookId()%></th>
								<td><img
									src="<%=request.getContextPath()%>/viewImg?bookId=<%=b.getBookId()%>"
									alt="<%=b.getPhotoName()%>">
								</td>
								<td><%=b.getBookname()%></td>
								<td><%=b.getAuthor()%></td>
								<td><%=b.getPrice()%></td>
								<td><%=b.getBookCategory()%></td>
								<td><%=b.getStatus()%></td>
								<td><a
									href="editbook.jsp?bookId=<%=b.getBookId()%>"
									class="btn btn-primary mx-1 my-1"> <i
										class="fa-solid fa-pen-to-square"></i> Edit
								</a> <a href="../admin/deleteBook?bookId=<%=b.getBookId()%>"
									class="btn btn-danger mx-1 my-1"> <i
										class="fa-solid fa-trash-can"></i> Delete
								</a></td>
							</tr>
							<%
							}
							} else {
							%>
							<tr>
								<td colspan="8" class="text-center">No books found.</td>
							</tr>
							<%
							}
							%>
						</tbody>
					</table>
				</div>

			</div>
		</div>
	</div>

	<div class="mt-5">
		<%@include file="footer.jsp"%>
	</div>

</body>
</html>
