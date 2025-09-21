<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.entity.Admin"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin: AddBook</title>
<%@include file="all_css.jsp"%>
<style>
body {
	background-color: #edede9;
}
</style>
</head>
<body>
	<%@include file="navbar.jsp"%>

	<c:if test="${empty adminobj}">
		<c:redirect url="/admin/login.jsp"></c:redirect>
	</c:if>
	<%-- 	<%Admin admin =(Admin) request.getAttribute("adminobj"); %>
 --%>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-4 mx-auto my-4">
				<div class="card">
					<div class="card-body">
						<h2 class="text-center text-primary my-3">Add Book</h2>

						<c:if test="${not empty succMsg}">
							<p class="text-center text-success fs-4">${succMsg}</p>
							<c:remove var="succMsg" scope="session" />
						</c:if>

						<c:if test="${not empty errorMsg}">
							<p class="text-center text-danger fs-4">${errorMsg}</p>
							<c:remove var="errorMsg" scope="session" />
						</c:if>



						<form action="<c:url value='/admin/addBook'/>" method="post"
							enctype="multipart/form-data">
							<div class="mb-3">
								<label class="form-label">Book Name* </label> <input type="text"
									name="bookname" class="form-control" required>
							</div>
							<div class="mb-3">
								<label class="form-label">Author Name* </label> <input
									type="text" name="authorname" class="form-control" required>
							</div>
							<div class="mb-3">
								<label class="form-label">Price* </label> <input type="number"
									name="price" class="form-control" required>
							</div>
							<div class="mb-3">
								<label class="form-label">Book Categories </label> <select
									required name="btype" class="form-control ">
									<option selected>--select--</option>
									<option value="New">New Book</option>
									<option value="Old">Old Book</option>
									<option value="Recent">Recent Book</option>
								</select>
							</div>
							<div class="mb-3">
								<label for="inputState" class="form-label">Book Status </label>
								<select id="inputState" class="form-control form-control"
									required name="bstatus">
									<option selected>--select--</option>
									<option value="Active">Active</option>
									<option value="Inactive">Inactive</option>
								</select>
							</div>
							<div class="mb-3">
								<label for="labelpwd" class="form-label">Upload Photo </label> <input
									type="file" name="imgs" accept="image/*"
									class="form-control-file" required>
							</div>



							<div class="d-grid gap-2 col-12 mx-auto">
								<button class="btn btn-primary" type="submit">Add</button>
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