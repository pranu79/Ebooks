<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.LinkedHashMap"%>
<%@ page import="com.entity.Order"%>
<%@ page import="com.entity.User"%>
<%@ page import="com.entity.OrderItem"%>
<%@ page import="com.dao.OrderDao"%>
<%@ page import="com.db.DBConnect"%>





<html>
<head>
<title>My Orders</title>
<%@include file="All_Components/all_css.jsp"%>

<style>
body {
	font-family: Arial, sans-serif;
	margin: 20px;
	background: #f9f9f9;
}

h2 {
	color: #333;
}

.order-card {
	background: #fff;
	border-radius: 8px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
	padding: 15px;
	margin-bottom: 20px;
}

.order-header {
	font-weight: bold;
	margin-bottom: 10px;
	border-bottom: 1px solid #ddd;
	padding-bottom: 5px;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
	font-size: 15px;
}

.table tr:hover {
	background-color: #f1f1f1;
}

th, td {
	padding: 8px 10px;
	border-bottom: 1px solid #eee;
	text-align: left;
}

th {
	background: #f2f2f2;
}

td.right {
	text-align: right;
}

.status-success {
	color: green;
	font-weight: bold;
}

.status-failed {
	color: red;
	font-weight: bold;
}
</style>
</head>
<body>
	<%@include file="All_Components/navbar.jsp"%>

	<c:if test="${empty userobj}">
		<c:redirect url="login.jsp"></c:redirect>
	</c:if>

	<div class="container text-center ">


		<h2 class="text-center">My Orders</h2>

		<%
		User u = (User) session.getAttribute("userobj");
		OrderDao dao = new OrderDao(DBConnect.getCon());
		List<Order> orders = dao.getOrderByUser(u.getEmail());

		// Group orders by orderId
		Map<String, Order> groupedOrders = new LinkedHashMap<>();
		if (orders != null) {
			for (Order o : orders) {
				groupedOrders.put(o.getOrderid(), o);
			}
		}
		%>


		<%
		if (groupedOrders.isEmpty()) {
		%>
		<p>No orders found.</p>
		<%
		} else {
		for (Order order : groupedOrders.values()) {
		%>
		<div class="order-card">
			<div class="order-header">
				Order ID:
				<%=order.getOrderid()%>
				| Date:
				<fmt:formatDate value="<%=order.getCreatedAt()%>"
					pattern="d MMM yyyy hh:mm a" />

				| Payment:
				<%=order.getPaymentType()%>
				| Status: <span
					class="<%="SUCCESS".equals(order.getStatus()) || "CAPTURED".equalsIgnoreCase(order.getStatus()) ? "status-success" : "status-failed"%>">
					<%=order.getStatus()%>
				</span>
			</div>

			<table>
				<tr>
					<th>Book Name</th>
					<th>Author</th>
					<th>Qty</th>
					<th class="text-center">Price</th>
				</tr>
				<%
				for (OrderItem item : order.getItems()) {
				%>
				<tr>
					<td><%=item.getBookName()%></td>
					<td><%=item.getAuthor()%></td>
					<td><%=item.getQuantity()%></td>
					<td class="text-center">₹<%=item.getPrice()%></td>
				</tr>
				<%
				}
				%>
				<tr>
					<td colspan="3" style="text-align: right; font-weight: bold;">Total:</td>
					<td class="text-center" style="font-weight: bold;">₹<%=order.getTotalAmount()%></td>
				</tr>
			</table>


		</div>
		<%
		}
		}
		%>
	</div>
	<%@include file="All_Components/footer.jsp"%>
</body>
</html>
