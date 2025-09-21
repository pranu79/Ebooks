package com.user.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.CartDao;
import com.dao.OrderDao;
import com.db.DBConnect;
import com.dto.ErrorResponse;
import com.entity.Cart;
import com.entity.Order;
import com.entity.OrderItem;
import com.util.AppConstants;
import com.util.ErrorCode;

@WebServlet("/admin/orderBook")
public class OrderBook extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			String phno = req.getParameter("phno");
			String address = req.getParameter("address");
			String landmark = req.getParameter("landmark");
			String state = req.getParameter("state");
			String city = req.getParameter("city");
			String pincode = req.getParameter("zip");
			String paymentType = req.getParameter("pmode");
			String author = req.getParameter("author");
			String fulladdress = address + " " + landmark + " " + state + " " + city + " " + pincode;

			CartDao cartDao = new CartDao(DBConnect.getCon());
			List<Cart> list = cartDao.getBookByUserId(id);

			// cart is empty redirect
			if (list.isEmpty()) {
				session.setAttribute("errorMsg", "Add Items");
				resp.sendRedirect("checkout.jsp");
			}

			if ("select".equals(paymentType)) {
				session.setAttribute("errorMsg", "Please select Payment Type");
				resp.sendRedirect("checkout.jsp");
			}

			// generate orderId
			String orderId = ("BOOK-ORD-00" + new Random().nextInt(1000)).toString();

			// create order
			Order o = new Order();
			o.setOrderid(orderId);
			o.setUsername(name);
			o.setEmail(email);
			o.setPhno(phno);
			o.setFulladdress(fulladdress);
			o.setPaymentType(paymentType);
			o.setStatus(AppConstants.STATUS_SUCCESS);
			o.setCreatedAt(new Timestamp(System.currentTimeMillis()));

			double total = 0.0;
			List<OrderItem> orderItems = new ArrayList<>();

			// add items to list
			for (Cart c : list) {
				total += c.getPrice() * c.getQuantity();

				OrderItem item = new OrderItem();
				item.setOrderId(o.getOrderid());
				item.setBookId(c.getBid());
				item.setBookName(c.getBook_name());
				item.setAuthor(c.getAuthor());
				item.setQuantity(c.getQuantity());
				item.setPrice(c.getPrice());
				orderItems.add(item);
			}

			o.setTotalAmount(total);
			o.setItems(orderItems);

			System.out.println(o);

			// If ONLINE payment → forward to CreateOrder
			if ("ONLINE".equals(paymentType)) {
				session.setAttribute("list2", o);
				RequestDispatcher rd = req.getRequestDispatcher("/CreateOrder");
				rd.forward(req, resp);
				return;
			}

			// save order
			OrderDao orderDao = new OrderDao(DBConnect.getCon());
			boolean orderSaved = orderDao.saveOrder(o);

			if (orderSaved) {
				cartDao.deleteCart(id);

				session.setAttribute("list2", o);
				resp.sendRedirect("orderSuccess.jsp");

			} else {

				session.setAttribute("errorMsg", "Sorry! Order Failed");
				resp.sendRedirect("checkout.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();

			ErrorResponse errorResponse = new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR.getName(),
					ErrorCode.INTERNAL_SERVER_ERROR.getCode(),ErrorCode.INTERNAL_SERVER_ERROR.getMessage());
			System.out.println(
					"Forwarding ErrorResponse -> " + errorResponse.getCode() + " | " + errorResponse.getMessage());
			req.setAttribute("errorResponse", errorResponse);

			// ✅ Forward instead of redirect
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		}

	}
}