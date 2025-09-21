<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Error Page</title>
<style>
body {
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	background-color: #f8f9fa;
	text-align: center;
	margin: 0;
	padding: 50px;
}

.error-box {
	display: inline-block;
	background: #fff;
	border: 1px solid #e0e0e0;
	padding: 30px 40px;
	border-radius: 12px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
	max-width: 500px;
}

h2 {
	color: #dc3545;
	margin-bottom: 15px;
}

p {
	font-size: 15px;
	margin: 8px 0;
}

.error-detail {
	text-align: left;
	margin-top: 20px;
	background: #f8d7da;
	border: 1px solid #f5c6cb;
	color: #721c24;
	border-radius: 8px;
	padding: 15px;
	font-size: 14px;
}

a {
	display: inline-block;
	margin-top: 20px;
	background: #007bff;
	color: #fff;
	padding: 10px 20px;
	border-radius: 6px;
	text-decoration: none;
	font-weight: 500;
}

a:hover {
	background: #0056b3;
}
</style>
</head>
<body>
	<div class="error-box">
		<h2>❌ Oops! Something went wrong</h2>
		<p>Please try again or contact support if the issue persists.</p>

		<div class="error-detail">
			<!-- Custom error set from servlet -->
			<c:if test="${not empty errorResponse}">
				<p>
					<b>Error Code:</b> ${errorResponse.code}
				</p>
				<p>
					<b>Name:</b> ${errorResponse.name}
				</p>
				<p>
					<b>Message:</b> ${errorResponse.message}
				</p>
			</c:if>

			<!-- Global container-provided errors -->
			<c:if test="${empty errorResponse}">
				<p>
					<b>Status Code:</b>
					${requestScope['javax.servlet.error.status_code']}
				</p>
				<p>
					<b>Message:</b> ${requestScope['javax.servlet.error.message']}
				</p>
				<p>
					<b>Exception Type:</b>
					${requestScope['javax.servlet.error.exception_type']}
				</p>
				<p>
					<b>Request URI:</b>
					${requestScope['javax.servlet.error.request_uri']}
				</p>
			</c:if>
		</div>

		<!-- Show admin-specific home link if admin is logged in -->
		<c:if test="${not empty adminobj}">
			<a href="<c:url value='/admin/home.jsp'/>">🔄 Go Back Admin Home</a>
		</c:if>

		<!-- Show user-specific home link if user is logged in -->
		<c:if test="${not empty userobj}">
			<a href="<c:url value='/index.jsp'/>">🔄 Go Back User Home</a>
		</c:if>

		<!-- Show generic homepage link only when nobody is logged in -->
		<c:if test="${empty adminobj and empty userobj}">
			<a href="<c:url value='/'/>">🏠 Go to Homepage</a>
		</c:if>

	</div>
</body>
</html>
