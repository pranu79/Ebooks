<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="com.entity.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Processing Payment...</title>
<style>
body {
	margin: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	background: #f9f9f9;
	font-family: Arial, sans-serif;
}

.loader {
	text-align: center;
}

.spinner {
	width: 50px;
	height: 50px;
	border: 5px solid #ddd;
	border-radius: 50%;
	animation: spin 1s linear infinite;
	margin: auto;
	position: relative;
	border-top: 5px solid #3399cc; .
	spinner: :before{
                position: absolute;
	top: 0;
	left: 0;
	border-top: 5px solid #3399cc;
}

}
@
keyframes spin { 0% {
	transform: rotate(0deg);
}

100


%
{
transform


:


rotate
(


360deg


)
;


}
}
p {
	margin-top: 15px;
	color: #555;
}
</style>

<script src="https://checkout.razorpay.com/v1/checkout.js"></script>

<script>
        function openRazorpay(orderId, name, email, phno, amount,key) {
        	
        	
            var options = {
                "key": key,   // replace with your Razorpay key
                "amount": amount * 100,      // amount in paise
                "currency": "INR",
                "name": "E-Book Store",
                "description": "Book Transaction",
                "image":"https://img.freepik.com/premium-vector/ebook-logo_497046-138.jpg",
                "order_id": orderId,         // from servlet
                "handler": function (response){
                    // Send payment details to verification servlet
                    
                    fetch("CaptureOrder", {
                        method: "POST",
                        headers: { "Content-Type": "application/x-www-form-urlencoded" },
                        body:"razorpay_payment_id=" + response.razorpay_payment_id +
                              "&razorpay_order_id=" + response.razorpay_order_id +
                              "&razorpay_signature=" + response.razorpay_signature
                    })
                    .then(res => res.text())
                    .then(data => {
                        if (data.includes("Successful")) {
                            window.location.href = "orderSuccess.jsp";
                        } else {
                            window.location.href = "checkout.jsp";
                        }
                    });
                },
                "prefill": {
                    "name": name,
                    "email": email,
                    "contact": phno
                },
                "theme": {
                    "color": "#3399cc"
                },
                "config": {
                	  "display": {
                	    "hide": ["country"]   // hides the country dropdown
                	  }
                	},

                "modal": {
                    "ondismiss": function () {
                        // ❌ Popup closed without payment
                        // Optional: Redirect to a custom failure page instead of reloading

                         window.location.href = "checkout.jsp";
                    }
                }
            };
            var rzp1 = new Razorpay(options);
            rzp1.open();
        }
    </script>
</head>
<body>
	<c:if test="${empty userobj}">
		<c:redirect url="login.jsp"></c:redirect>
	</c:if>
	<%User u = (User) session.getAttribute("userobj");
%>
	<div class="loader">
		<div class="spinner"></div>
		<p>Redirecting to secure payment...</p>
	</div>

	<script type="text/javascript">
	openRazorpay('<%=request.getAttribute("razorpay_orderId")%>', 
                             '<%=request.getAttribute("name")%>', 
                             '<%=request.getAttribute("email")%>', 
                             '<%=request.getAttribute("phno")%>', 
                             '<%=request.getAttribute("amount")%>',
                             '<%=request.getAttribute("key")%>'
                             )
                             </script>

</body>


</body>
</html>