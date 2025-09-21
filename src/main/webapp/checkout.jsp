<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="com.db.DBConnect"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="com.dao.CartDao"%>
<%@page import="com.dao.UserDao"%>
<%@page import="com.entity.Books"%>
<%@page import="java.util.List"%>
<%@page import="com.entity.Cart"%>
<%@page import="com.entity.User"%>
<%@page import="com.util.AppConstants"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>E-Book: Cart</title>
<%@include file="All_Components/all_css.jsp"%>

<style>
body {
	background-color: #edede9;
}
</style>

<script src="https://checkout.razorpay.com/v1/checkout.js"></script>
</head>
<body>
	<%@include file="All_Components/navbar.jsp"%>

	<c:if test="${empty userobj }">
		<c:redirect url="login.jsp"></c:redirect>
	</c:if>

	<c:if test="${not empty cartMsg }">
		<div class="alert alert-success text-center " role="alert">${cartMsg}</div>
		<c:remove var="cartMsg" scope="session" />
	</c:if>

	<c:if test="${not empty errorMsg }">
		<div class="alert alert-danger text-center" role="alert">${errorMsg.message}</div>
		<c:remove var="errorMsg" scope="session" />
	</c:if>

	<div class="container mt-3">
		<div class="row p-2">
			<!-- Cart Items -->
			<div class="col-lg-6 col-md-12 mb-3">
				<div class="card bg-white shadow-sm">
					<div class="card-body">
						<h3 class="text-center text-success">Your Selected Item</h3>
						<hr>
						<div class="table-responsive">
							<table class="table text-center align-middle">
								<thead class="table-light">
									<tr>
										<th scope="col">Book Name</th>
										<th scope="col">Author</th>
										<th scope="col">Price</th>
										<th scope="col" style="width: 100px;">Quantity</th>
										<th scope="col">Action</th>
									</tr>
								</thead>
								<tbody>
									<%
									User u = (User) session.getAttribute("userobj");
									CartDao dao = new CartDao(DBConnect.getCon());
									List<Cart> cartList = dao.getBookByUserId(u.getId());

									int total = 0;
									for (Cart c : cartList) {
										total = c.getTotal_price();
									%>
									<tr>
										<td scope="row"><%=c.getBook_name()%></td>
										<td scope="row"><%=c.getAuthor()%></td>
										<td scope="row">₹<%=c.getPrice()%></td>
										<%-- 									<td scope="row"><%=c.getQuantity()%></td>
 --%>
										<td>
											<form action="Cart" method="get" style="display: inline;">
												<input type="hidden" name="bookId" value="<%=c.getBid()%>">
												<input type="hidden" name="userId" value="<%=c.getUid()%>">
												<input type="hidden" name="action" value="update"> <input
													class="form-control form-control-sm text-center qty-input"
													text-align: center;" type="number" name="quantity"
													value="<%=c.getQuantity()%>" min="1"
													onchange="this.form.submit()">
											</form>
										</td>

										<td><a
											href="removeBook?bid=<%=c.getBid()%>&&uid=<%=c.getUid()%>&&cid=<%=c.getCid()%>"
											class="btn btn-danger btn-sm" type="submit"> <i
												class="fa-solid fa-trash-can fa-danger"></i> Remove
										</a></td>
									</tr>
									<%
									}
									%>
									<tr>
										<td scope="row" colspan="2"><b>Total Price</b></td>
										<td scope="row"><b>₹<%=total%></b></td>
										<td colspan="2"></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>

			<!-- Order Form -->
			<div class="col-lg-6 col-md-12">
				<div class="card shadow-sm">
					<div class="card-body">
						<h3 class="text-center text-success">Your Details for Order</h3>

						<form action="orderBook" method="post" id="checkout">
							<div class="row g-3 mt-2">
								<input type="hidden" name="amount" value="<%=total%>"> <input
									type="hidden" name="id" value="${userobj.id}">

								<div class="col-md-6">
									<label class="form-label">Name </label> <input type="text"
										name="name" class="form-control" value="${userobj.name}"
										required>
								</div>
								<div class="col-md-6">
									<label class="form-label">Email </label> <input type="email"
										name="email" class="form-control" value="${userobj.email}"
										required>
								</div>
								<div class="col-md-6">
									<label class="form-label">Mobile No </label> <input
										type="number" name="phno" class="form-control"
										value="${userobj.phno}" required>
								</div>
								<div class="col-md-6">
									<label class="form-label">Address</label> <input type="text"
										name="address" class="form-control" value="${userobj.address}"
										required>
								</div>
								<div class="col-md-6">
									<label class="form-label">Landmark </label> <input type="text"
										name="landmark" class="form-control"
										value="${userobj.landmark}" required>
								</div>
								<div class="col-md-6">
									<label class="form-label">State </label> <input type="text"
										name="state" class="form-control" value="${userobj.state}"
										required>
								</div>
								<div class="col-md-6">
									<label class="form-label">City </label> <input type="text"
										name="city" class="form-control" value="${userobj.city}"
										required>
								</div>
								<div class="col-md-6">
									<label class="form-label">Zip Code </label> <input
										type="number" name="zip" class="form-control"
										value="${userobj.pincode}" required>
								</div>

								<div class="col-md-12">
									<label class="form-label">Payment Type</label> <select
										class="form-select" name="pmode" required>
										<option selected value="select">-- Select --</option>
										<option value="COD">Cash on Delivery</option>
										<option value="ONLINE">Pay Online</option>
									</select>
								</div>

								<div class="col-12 text-center mt-3">
									<button class="btn btn-warning" type="button"
										onclick="placeOrder()">Order Now</button>
									<a href="index.jsp" class="btn btn-primary">Continue
										Shopping</a>
								</div>
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

	<script>
function placeOrder() {
    let paymentMode = document.querySelector('select[name="pmode"]').value;
    let amount = "<%=total%>";

    if (paymentMode === "COD") {
        document.getElementById("checkout").submit();
    } else if (paymentMode === "ONLINE") {
        // First call backend to create Razorpay Order
        fetch("createRazorpayOrder", {
            method: "POST",
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: "amount=" + amount
        })
        .then(response => response.json())
        .then(data => {
            var options = {
                "key": "<%=AppConstants.RAZORPAY_KEY%>",
                "amount": data.amount,
                "currency": data.currency,
                "order_id":data.id,
                "name": "E-Book Store",
                "description": "Payment for Order",
                "order_id": data.id,   // â Razorpay order_id
                "handler": function (response) {
                    var form = document.getElementById("checkout");

                    // Add Razorpay details into form
                    ["razorpay_payment_id", "razorpay_order_id", "razorpay_signature"].forEach(field => {
                        var input = document.createElement("input");
                        input.type = "hidden";
                        input.name = field;
                        input.value = response[field];
                        form.appendChild(input);
                    });

                    form.submit();
                },
                "prefill": {
                    "name": "${userobj.name}",
                    "email": "${userobj.email}",
                    "contact": "${userobj.phno}"
                },
                "theme": { "color": "#3399cc" }
            };
            var rzp = new Razorpay(options);
            
         // Handle payment failure or popup close
            rzp.on('payment.failed', function(response){
                alert("Payment failed: "+response.error.description);
            });
            rzp.open();
        })
        .catch(err => alert("Error creating order: " + err));
    }else {
        alert("Please select a valid payment method!");
    }
}

</script>
</body>
</html>
