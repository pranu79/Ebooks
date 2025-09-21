<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>E-Book: Register</title>
<%@include file="All_Components/all_css.jsp"%>
<style>
body {
	background-color: #edede9;
}

.error {
	color: red;
}
</style>
</head>
<body>

	<%@include file="All_Components/navbar.jsp"%>

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-4 mx-auto my-4">
				<div class="card">

					<h2 class="text-center text-dark my-3">Registration Page</h2>

					<c:if test="${not empty succMsg}">
						<p class="text-center text-success fs-4">${succMsg}</p>
						<c:remove var="succMsg" scope="session" />
					</c:if>

					<c:if test="${not empty errorMsg}">
						<p class="text-center text-danger fs-5">${errorMsg}</p>
						<c:remove var="errorMsg" scope="session" />
					</c:if>
					<div class="card-body">

						<form action="register" id="register" method="post">
							<div class="mb-3">
								<label for="labelname" class="form-label">Name </label> <input
									type="text" name="name" id="labelname" class="form-control"
									placeholder="Enter Your Name" required>
							</div>
							<div class="mb-3">
								<label for="labelemail" class="form-label">Email Address
								</label> <input type="email" name="email" id="labelemail"
									class="form-control" placeholder="Enter Your Email Address"
									required>
							</div>
							<div class="mb-3">
								<label for="labelno" class="form-label">Phone No </label> <input
									type="text" name="phno" id="labelno" class="form-control"
									placeholder="Enter Your Phone No" required maxlength="10">
							</div>
							<div class="mb-3">
								<label for="labelpwd" class="form-label">Password </label> <input
									type="password" name="password" id="labelpwd"
									class="form-control" placeholder="Enter Password" required>
							</div>

							<div class="mb-3">
								<div class="form-check">
									<input class="form-check-input" type="checkbox" id="Check"
										name="check"> <label class="form-check-label"
										for="Check">Agree Terms & Conditions</label>
								</div>
							</div>
							<div class="d-grid gap-2 col-12 mx-auto">
								<button class="btn btn-success" type="submit">Register</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="mt-100px">
		<%@include file="All_Components/footer.jsp"%>
	</div>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/register.js"></script>
</body>
</html>