<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>

<%@page import="com.db.DBConnect"%>
<%@page import="com.dao.BookDao"%>
<%@page import="com.entity.Books"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin: Edit Book</title>
<%@include file="all_css.jsp"%>
<style>
body {
	background-color: #edede9;
}
</style>
</head>
<body>
	<c:if test="${empty adminobj}">
		<c:redirect url="/admin/login.jsp"></c:redirect>
	</c:if>

	<%@include file="navbar.jsp"%>

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-4 mx-auto my-4">
				<div class="card">

					<h2 class="text-center text-primary my-3">Update Book Details</h2>
					<div class="card-body">

						<%
						int bookId = Integer.parseInt(request.getParameter("bookId"));
						BookDao dao = new BookDao(DBConnect.getCon());
						Books b = dao.getBookById(bookId);
						%>


						<form action="<c:url value='/admin/editBook'/>" method="post">

							<div class="mb-3">
								<label class="form-label">Book Name* </label> <input type="text"
									name="bookname" class="form-control"
									value="<%=b.getBookname()%>" required>
							</div>
							<div class="mb-3">
								<label class="form-label">Author Name* </label> <input
									type="text" name="authorname" class="form-control"
									value="<%=b.getAuthor()%>" required>
							</div>
							<div class="mb-3">
								<label class="form-label">Price* </label> <input type="number"
									name="price" class="form-control" value="<%=b.getPrice()%>"
									required>
							</div>

							<div class="mb-3">
								<label for="inputState" class="form-label">Book Status </label>
								<select id="inputState" class="form-control form-control"
									name="bstatus" required>
									<%
									if ("active".equals(b.getStatus())) {
									%>
									<option value="Active">Active</option>
									<option value="Inactive">Inactive</option>
									<%
									} else {
									%>
									<option value="Inactive">Inactive</option>
									<option value="Active">Active</option>
									<%
									}
									%>

								</select>
							</div>

							<input type="hidden" name="id" value="<%=b.getBookId()%>">


							<div class="d-grid gap-2 col-12 mx-auto">
								<button class="btn btn-primary" type="submit">Update</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>





	<div class="mt-100px">
		<%@include file="footer.jsp"%>
	</div>



</body>
</html>