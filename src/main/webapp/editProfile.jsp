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
<%@include file="All_Components/all_css.jsp"%>

<title>Setting: Edit Profile</title>
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

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-4 mx-auto my-4">
				<div class="card">
					<div class="card-body">
						<h2 class="text-center text-primary mb-2">Edit Profile</h2>

						<c:if test="${not empty succMsg }">
							<p class="text-center text-success fs-4">${succMsg}</p>
							<c:remove var="succMsg" scope="session" />
						</c:if>

						<c:if test="${not empty errorMsg }">
							<p class="text-center text-danger fs-4">${errorMsg}</p>
							<c:remove var="errorMsg" scope="session" />
						</c:if>


						<c:if test="${not empty userobj}">

							<form action="editProfile" method="post">

								<input type="hidden" name="uid" value="${userobj.id }">
								<div class="mb-3">
									<label for="labelname" class="form-label">Name </label> <input
										type="text" name="name" id="labelname" class="form-control"
										value="${userobj.name }" required>
								</div>
								<div class="mb-3">
									<label for="labelemail" class="form-label">Email
										Address </label> <input type="email" name="email" id="labelemail"
										class="form-control" value="${userobj.email}" required>
								</div>
								<div class="mb-3">
									<label for="labelno" class="form-label">Phone No </label> <input
										type="text" name="phno" id="labelno" class="form-control"
										value="${userobj.phno}" required>
								</div>
								<!-- Current password (for verification) -->
								<div class="mb-3">
									<label for="labelpwd" class="form-label">Current
										Password</label> <input type="password" name="password" id="labelpwd"
										class="form-control" required>
								</div>

								<!-- New password (optional) -->
								<div class="mb-3">
									<label for="labelnewpwd" class="form-label">New
										Password (optional)</label> <input type="password" name="newPassword"
										id="labelnewpwd" class="form-control"
										placeholder="Leave blank if not changing">
								</div>
						</c:if>
						<div class="d-grid gap-4 col-4 mx-auto">
							<button class="btn btn-primary btn" type="submit">Update</button>
						</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div style="margin-top: 10px">
		<%@include file="All_Components/footer.jsp"%>
	</div>
</body>
</html>