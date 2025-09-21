<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<%@page import="com.db.DBConnect"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="com.dao.UserDao"%>
<%@page import="com.entity.User"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="All_Components/all_css.jsp"%>

<title>Setting: Edit Address</title>
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
			<div class="col-md-6 mx-auto my-4 ">
				<div class="card">
					<div class="card-body">
						<h2 class="text-center text-primary my-3">Your Address</h2>


						<c:if test="${not empty succMsg }">
							<p class="text-center text-success fs-4">${succMsg}</p>
							<c:remove var="succMsg" scope="session" />
						</c:if>

						<c:if test="${not empty errorMsg }">
							<p class="text-center text-danger fs-5">${errorMsg}</p>
							<c:remove var="errorMsg" scope="session" />
						</c:if>


						<form action="addAddress" method="post">

							<input type="hidden" name="id" value="${userobj.id }">
							<div class="row p-3">
								<div class="col-md-6 mb-3">
									<label for="labelname" class="form-label">Address</label> <input
										type="text" name="address" id="labelno" class="form-control"
										value="${userobj.address}" required>

								</div>
								<div class="col-md-6 mb-3">
									<label for="labelname" class="form-label">Landmark </label> <input
										type="text" name="landmark" id="labelname"
										class="form-control" value="${userobj.landmark}" required>
								</div>
								<div class="col-md-4 mb-3">
									<label for="labelname" class="form-label">State </label> <input
										type="text" name="state" id="labelname" class="form-control"
										value="${userobj.state}" required>
								</div>
								<div class="col-md-4 mb-3">
									<label for="labelname" class="form-label">City </label> <input
										type="text" name="city" id="labelname" class="form-control"
										value="${userobj.city}" required>
								</div>
								<div class="col-md-4 mb-3">
									<label for="labelname" class="form-label">Zip Code </label> <input
										type="number" name="zip" id="labelname" class="form-control"
										value="${userobj.pincode}" required>
								</div>


								<div class="mt-3 text-center">
									<button class="btn btn-warning " type="submit">Add
										Address</button>
								</div>
							</div>
						</form>

					</div>
				</div>
			</div>
		</div>
	</div>
	<div style="margin-top: 45px">
		<%@include file="All_Components/footer.jsp"%>
	</div>
</body>
</html>