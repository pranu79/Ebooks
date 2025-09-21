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

<title>Setting: Service</title>
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

	<div class="container my-2">
		<div class="row p-5">
			<div class="col-md-4 offset-md-4 text-center">
				<div class="text-success ">
					<i class="fas fa-phone-square fa-5x"></i>
				</div>
				<h3>24*7 Service</h3>
				<h4>Helpline Number</h4>
				<h5>+91 1234234567</h5>
				<a href="index.jsp" class="btn btn-primary">Home</a>
			</div>
		</div>
	</div>


	<div style="margin-top: 123px">
		<%@include file="All_Components/footer.jsp"%>
	</div>
</body>
</html>