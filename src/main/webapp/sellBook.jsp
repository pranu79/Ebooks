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
<title>Setting: SellBook</title>
<%@include file="All_Components/all_css.jsp"%>

</head>
<body>
	<%@include file="All_Components/navbar.jsp"%>
	<c:if test="${empty userobj}">
		<c:redirect url="login.jsp"></c:redirect>
	</c:if>

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-4 mx-auto my-4">
				<div class="card">
					<div class="card-body">
						<h2 class="text-center text-primary mb-2">Sell Old Book</h2>

						<c:if test="${not empty succMsg }">
							<p class="text-center text-success fs-4">${succMsg}</p>
							<c:remove var="succMsg" scope="session" />
						</c:if>

						<c:if test="${not empty errorMsg }">
							<p class="text-center text-danger fs-5">${errorMsg}</p>
							<c:remove var="errorMsg" scope="session" />
						</c:if>

						<form action="addOldbook" method="post"
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
								<label for="labelpwd" class="form-label">Upload Photo </label> <input
									type="file" name="imgs" class="form-control" required>
							</div>

							<input type="hidden" name="email" value="${userobj.email}"
								required>



							<div class="d-grid gap-4 col-2 mx-auto">
								<button class="btn btn-primary btn" type="submit">Sell</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div style="margin-top: 8px">
		<%@include file="All_Components/footer.jsp"%>
	</div>
</body>
</html>