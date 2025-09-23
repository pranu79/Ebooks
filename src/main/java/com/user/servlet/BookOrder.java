package com.user.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.CartDao;
import com.dao.OrderDao;
import com.db.DBConnect;
import com.entity.Cart;
import com.entity.Order;
import com.entity.OrderItem;
import com.util.AppConstants;
import com.util.RazorpaySignatureVerifier;

@WebServlet("/orderBook")
public class BookOrder extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        System.out.println("BookOrder servlet called!");  // debug


		HttpSession session = request.getSession();

		String razorpayPaymentId = request.getParameter("razorpay_payment_id");
		String razorpayOrderId = request.getParameter("razorpay_order_id");
		String razorpaySignature = request.getParameter("razorpay_signature");

		String pmode = request.getParameter("pmode");
		int userId = Integer.parseInt(request.getParameter("id"));
		String address = request.getParameter("address");
		String landmark = request.getParameter("landmark");
		String state = request.getParameter("state");
		String city = request.getParameter("city");
		String zipcode = request.getParameter("zip");

		String fullAddress = address + ", " + landmark + ", " + city + ", " + state + " - " + zipcode;

		// Verify signature for ONLINE payment
		if ("ONLINE".equals(pmode)) {
			boolean isValid = RazorpaySignatureVerifier.verify(razorpayOrderId, razorpayPaymentId, razorpaySignature);
			if (!isValid) {
				session.setAttribute("failedMsg", "Payment verification failed!");
				response.sendRedirect("checkout.jsp");
				return;
			}
		}
		// Generate unique order ID
		String orderId = "BOOK-ORD-00" + new Random().nextInt(1000);

		// Build Order object
		Order order = new Order();
		order.setOrderid(orderId);
		order.setUsername(request.getParameter("name"));
		order.setEmail(request.getParameter("email"));
		order.setPhno(request.getParameter("phno"));
		order.setFulladdress(fullAddress);
		order.setPaymentType(pmode);
		order.setStatus(AppConstants.STATUS_SUCCESS);
		order.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));

		List<OrderItem> orderItems = new ArrayList<>();

		// Fetch cart items
		CartDao cartDao = new CartDao(DBConnect.getCon());
		List<Cart> list = cartDao.getBookByUserId(userId);

		if (list.isEmpty() || list == null) {
			session.setAttribute("cartMsg", "Cart is Empty!");
			response.sendRedirect("checkout.jsp");
			return;
		}

		double total = 0.0;
		for (Cart c : list) {
			total += c.getPrice() * c.getQuantity();

			OrderItem item = new OrderItem();
			item.setOrderId(orderId);
			item.setBookId(c.getBid());
			item.setBookName(c.getBook_name());
			item.setAuthor(c.getAuthor());
			item.setQuantity(c.getQuantity());
			item.setPrice(c.getPrice());
			orderItems.add(item);
		}
		order.setTotalAmount(total);
		order.setItems(orderItems);

		 if("ONLINE".equals(pmode)){
             order.setRazorpayPaymentId(razorpayPaymentId);
             order.setRazorpayOrderId(razorpayOrderId);
             order.setPaymentSignature(razorpaySignature);
         }

         order.setStatus(AppConstants.STATUS_SUCCESS);


		// Save order in DB
		OrderDao orderDao = new OrderDao(DBConnect.getCon());
		boolean saved = orderDao.saveOrder(order);

		if (saved) {
			cartDao.deleteCart(userId); // Clear cart
			session.setAttribute("cartMsg", "Order placed successfully!");
			response.sendRedirect("orderSuccess.jsp");
		} else {
			session.setAttribute("failedMsg", "Something went wrong!");
			response.sendRedirect("checkout.jsp");
		}
	}
}
