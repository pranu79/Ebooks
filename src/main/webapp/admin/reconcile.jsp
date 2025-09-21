<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin: Reconcile Orders</title>
<%@include file="all_css.jsp"%>
<style>
body {
	background-color: #f8f9fa;
}

.card {
	margin-top: 30px;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
	border-radius: 10px;
}
</style>
</head>
<body>
	<c:if test="${empty adminobj}">
		<c:redirect url="/admin/login.jsp"></c:redirect>
	</c:if>
	<%@include file="navbar.jsp"%>

	<div class="container my-4">
		<div class="row">
			<div class="col-md-6 mx-auto ">

				<!-- Success & Error Messages -->
				<c:if test="${not empty succMsg}">
					<div class="alert alert-success text-center">${succMsg}</div>
					<c:remove var="succMsg" />
				</c:if>
				<c:if test="${not empty errorMsg}">
					<div class="alert alert-danger text-center">${errorMsg}</div>
					<c:remove var="errorMsg" />
				</c:if>

				<!-- Card -->
				<div class="card">
					<div class="card-header text-center bg-primary text-white">
						<h4>Manual Reconciliation</h4>
					</div>
					<div class="card-body">

						<!-- Reconcile Specific Order -->
						<form action="<c:url value='/admin/reconcileOrder'/>"
							method="post" class="mb-4">
							<div class="mb-3">
								<label class="form-label">Payment ID</label> <input type="text"
									name="paymentId" class="form-control"
									placeholder="Enter Razorpay Payment ID" required>
							</div>
							<div class="d-grid">
								<button type="submit" class="btn btn-success">Reconcile
									This Order</button>
							</div>
						</form>

						<hr>

						<!-- Reconcile All Pending Orders -->
						<form action="<c:url value='/admin/reconcileAll'/>" method="get">
							<div class="d-grid">
								<button type="submit" class="btn btn-warning">Reconcile
									All Pending Orders</button>
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
