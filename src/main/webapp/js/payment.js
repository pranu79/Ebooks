alert("helo");
function placeOrder() {
    let paymentMode = document.querySelector('input[name="pmode"]:checked').value;
    let amount = "<%= total %>";

    if (paymentMode === "COD") {
        // COD: directly call servlet to save order
        let form = document.getElementById("checkout");
        form.action = "orderBook";
        form.submit();
    } else {
        // ONLINE: Create Razorpay order
        fetch("CreateOrder?amount=" + amount)
            .then(res => res.json())   // ❌ Issue here
            .then(data => {
                var options = {
                    "key": data.key,
                    "amount": data.amount,
                    "currency": "INR",
                    "name": "Ebook Store",
                    "description": "Order Payment",
                    "order_id": data.orderId,
                    "handler": function (response) {
                        // Send payment details to CaptureOrderServlet
                        let form = document.createElement("form");
                        form.method = "POST";
                        form.action = "CaptureOrder";

                        form.innerHTML = `
                            <input type="hidden" name="razorpay_payment_id" value="${response.razorpay_payment_id}">
                            <input type="hidden" name="razorpay_order_id" value="${response.razorpay_order_id}">
                            <input type="hidden" name="razorpay_signature" value="${response.razorpay_signature}">
                        `;
                        document.body.appendChild(form);
                        form.submit();
                    }
                };
                var rzp = new Razorpay(options);
                rzp.open();
            });
    }
}
