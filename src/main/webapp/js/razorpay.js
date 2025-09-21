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