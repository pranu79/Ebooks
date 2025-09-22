<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="com.db.DBConnect"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.dao.OrderDao"%>
<%@page import="java.util.List"%>
<%@page import="com.entity.Order"%>
<%@page import="com.entity.OrderItem"%>

<%
OrderDao dao = new OrderDao(DBConnect.getCon());
List<Order> orderList = dao.getAllOrder();
request.setAttribute("orderList", orderList);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin: All Orders</title>
<%@include file="all_css.jsp"%>
<style>
body {
	background-color: #edede9;
}

.badge {
	font-size: 0.85rem;
}

.table thead th {
	background-color: #212529;
	color: #fff;
	text-align: center;
}

.table td, .table th {
	vertical-align: middle;
	text-align: center;
}

@media (max-width: 768px) {
	.table td, .table th {
		font-size: 0.75rem;
		padding: 0.35rem;
	}
	.btn {
		font-size: 0.7rem;
		padding: 0.25rem 0.5rem;
		margin-bottom: 2px;
	}
}
</style>
</head>
<body>
	<c:if test="${empty adminobj}">
		<c:redirect url="/admin/login.jsp"></c:redirect>
	</c:if>
	<%@include file="navbar.jsp"%>

	<h2 class="text-center my-3">All Orders</h2>

	<div class="container-fluid">
		<div class="row">
			<div class="col-12">

				<c:choose>
					<c:when test="${not empty orderList}">
						<div class="table-responsive">
							<table class="table table-bordered table-striped shadow-sm">
								<thead>
									<tr>
										<th>Order Id</th>
										<th>Customer</th>
										<th>Email</th>
										<th>Phone</th>
										<th>Amount</th>
										<th>Payment Type</th>
										<th>Payment Id</th>
										<th>Status</th>
										<th>Created Time</th>
										<th>Books</th>
									</tr>
								</thead>
								<tbody id="accordionOrders">
									<c:forEach var="order" items="${orderList}" varStatus="loop">
										<%
										List<OrderItem> items = dao.getOrderItems(((Order) pageContext.getAttribute("order")).getOrderid());
										request.setAttribute("items", items);
										%>
										<tr>
											<td>${order.orderid}</td>
											<td>${order.username}</td>
											<td>${order.email}</td>
											<td>${order.phno}</td>
											<td>₹ ${order.totalAmount}</td>
											<td>${order.paymentType}</td>
											<td>
												<c:choose>
													<c:when test="${not empty order.razorpayPaymentId}">
														${order.razorpayPaymentId}
													</c:when>
													<c:otherwise>-</c:otherwise>
												</c:choose>
											</td>
											<td>
												<c:choose>
													<c:when test="${order.status eq 'SUCCESS' || order.status eq 'CAPTURED'}">
														<span class="badge bg-success">Success</span>
													</c:when>
													<c:when test="${order.status eq 'PENDING'}">
														<span class="badge bg-warning text-dark">Pending</span>
													</c:when>
													<c:when test="${order.status eq 'CREATED'}">
														<span class="badge bg-info text-dark">Pending</span>
													</c:when>
													<c:when test="${order.status eq 'FAILED'}">
														<span class="badge bg-danger">Failed</span>
													</c:when>
													<c:otherwise>
														<span class="badge bg-secondary">${order.status}</span>
													</c:otherwise>
												</c:choose>
											</td>
											<td><fmt:formatDate value="${order.createdAt}" pattern="d MMM yyyy hh:mm a" /></td>
											<td>
												<button class="btn btn-sm btn-primary toggle-btn" type="button" data-bs-toggle="collapse" data-bs-target="#books-${loop.index}" aria-expanded="false" aria-controls="books-${loop.index}" data-bs-parent="#accordionOrders">
													Books (<%=items.size()%>)
												</button>
											</td>
										</tr>
										<tr>
											<td colspan="10" class="p-0">
												<div id="books-${loop.index}" class="collapse">
													<div class="table-responsive">
														<table class="table table-sm table-bordered mb-0">
															<thead class="table-light">
																<tr>
																	<th>Book</th>
																	<th>Author</th>
																	<th>Quantity</th>
																</tr>
															</thead>
															<tbody>
																<c:forEach var="item" items="${items}">
																	<tr>
																		<td>${item.bookName}</td>
																		<td>${item.author}</td>
																		<td>${item.quantity}</td>
																	</tr>
																</c:forEach>
															</tbody>
														</table>
													</div>
												</div>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</c:when>
					<c:otherwise>
						<div class="alert alert-info text-center">🚫 No orders found.</div>
					</c:otherwise>
				</c:choose>

			</div>
		</div>
	</div>

	<div style="margin-top: 250px">
		<%@include file="footer.jsp"%>
	</div>

	<!-- Bootstrap JS -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
