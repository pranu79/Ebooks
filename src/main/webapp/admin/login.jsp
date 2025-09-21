<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Admin Login</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<%@include file="all_css.jsp"%>
<style>
body {
	background-color: #edede9;
}
</style>
</head>
<body>
	<%@include file="navbar.jsp"%>


	<div class="container-fluid p-3">
		<div class="row justify-content-center">
			<div class="col-md-4">
				<div class="card">
					<div class="card-body">
						<h2 class="text-center text-dark  mt-2 mb-4">Admin Login</h2>


						<c:if test="${not empty succMsg }">
							<p class="text-center text-success fs-4">${succMsg}</p>
							<c:remove var="succMsg" scope="session" />
						</c:if>

						<c:if test="${not empty errorMsg }">
							<p class="text-center text-danger fs-5">${errorMsg}</p>
							<c:remove var="errorMsg" scope="session" />
						</c:if>

						<form action="<c:url value='/admin/login'/>" method="post"
							id="login">
							<div class="mb-3">
								<label for="labelemail" class="form-label">Email Address
								</label> <input type="email" name="email" id="labelemail"
									class="form-control" placeholder="Enter Your Email Address"
									required>
							</div>
							<div class="mb-3">
								<label for="labelpwd" class="form-label">Password </label> <input
									type="password" name="password" id="labelpwd"
									class="form-control" placeholder="Enter Password" required>
							</div>

							<div class="d-grid gap-2 col-6 mx-auto mb-4">
								<button class="btn btn-primary" type="submit">Login</button>
								<%-- <a href="${pageContext.request.contextPath}/register.jsp"
									class="text-center">Create Account</a> --%>
							</div>
						</form>


					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>

</body>
</html>
