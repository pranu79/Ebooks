<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<title>E-Book</title>
<%@include file="All_Components/all_css.jsp"%>
<style>
.crd-ho:hover {
	background-color: #edede9;
}
</style>
</head>
<body>
	<%@include file="All_Components/navbar.jsp"%>

	<%
	User u = (User) session.getAttribute("userobj");
	%>

	<!-- -start recent books -->

	<div class="container-fluid">
		<h3 class="text-center py-2">Recent Books</h3>
		<div class="row">

			<%
			String ch = request.getParameter("ch");
			BookDao dao = new BookDao(DBConnect.getCon());
			List<Books> list = dao.getBookBySearch(ch);
			for (Books b : list) {
			%>
		<div class="col-6 col-sm-6 col-md-3 col-lg-3">
				<div class="card crd-ho h-100">
					<div class="card-body text-center d-flex flex-column">
						<img
							src="<%=request.getContextPath()%>/viewImg?bookId=<%=b.getBookId()%>"
							alt="<%=b.getPhotoName()%>" />
						<p class="mb-1 fw-bold"><%=b.getBookname()%></p>
						<p class="mb-1"><%=b.getAuthor()%></p>
						<%
						if (b.getBookCategory().equals("Old")) {
						%>
						<p class="mb-2">
							Category:
							<%=b.getBookCategory()%></p>
						<div class="btn-group btn-group-sm mt-auto d-flex justify-content-center flex-wrap">
							<a href="view_details.jsp?bookId=<%=b.getBookId()%>"
								class="btn btn-success btn-sm">View Details</a> <a href=""
								class="btn btn-danger btn-sm"><i
								class="fa-solid fa-indian-rupee-sign" style="color: #f7f7f7;"></i><%=b.getPrice()%></a>
						</div>
						<%
						} else {
						%>

						<p class="mb-2">
							Category:
							<%=b.getBookCategory()%>
                        </p>

						<div class="btn-group btn-group-sm mt-auto d-flex justify-content-center flex-wrap">
							<%
							if (u == null) {
							%>
							<a href="login.jsp" class="btn btn-danger btn-sm "><i
								class="fa-solid fa-cart-shopping" style="color: #ffffff;"></i>
								Add Cart</a>
							<%
							} else {
							%>
							<a href="Cart?bookId=<%=b.getBookId()%>&&userId=<%=u.getId()%>"
								class="btn btn-danger btn-sm "><i
								class="fa-solid fa-cart-shopping" style="color: #ffffff;"></i>
								Add Cart</a>
							<%
							}
							%>

							<a href="view_details.jsp?bookId=<%=b.getBookId()%>"
								class="btn btn-success  btn-sm">View Details</a> <a href=""
								class="btn btn-danger  btn-sm "><i
								class="fa-solid fa-indian-rupee-sign" style="color: #f7f7f7;"></i><%=b.getPrice()%></a>
						</div>
						<%
						}
						%>
					</div>
				</div>
			</div>
			<%
			}
			%>
            <%
			if (list.isEmpty() || list == null) {
			%>
			<div class="alert alert-info text-center" role="alert">No books
				found</div>
			<%
			}
			%>

		</div>
		<div class="mb-0px">
			<%@include file="All_Components/footer.jsp"%>
		</div>
</body>
</html>
