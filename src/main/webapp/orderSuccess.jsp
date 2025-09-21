<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="java.util.List"%>
<%@page import="com.entity.Cart"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Thank You Page</title>
<%@include file="All_Components/all_css.jsp"%>
</head>
<body>
	<c:if test="${empty userobj}">
		<c:redirect url="login.jsp"></c:redirect>
	</c:if>
	<%@include file="All_Components/navbar.jsp"%>

	<div class="container text-center mt-5">

		<i class="fas fa-check-circle fa-5x text-success my-2"></i>

		<h1>Thank You</h1>
		<h2>Your Order has been Successful</h2>
		<h5>Within 7 days Your Product will be delivered on your Address</h5>
		<a href="index.jsp" class="btn btn-primary mt-3">Home</a> <a
			href="orderTrack.jsp" class="btn btn-danger mt-3">View Order</a>


	</div>

	<div class="mt-100px">
		<%@include file="All_Components/footer.jsp"%>
	</div>



</body>
</html>