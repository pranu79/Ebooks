package com.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.BookDao;
import com.dao.CartDao;
import com.db.DBConnect;
import com.dto.ErrorResponse;
import com.entity.Books;
import com.entity.Cart;
import com.util.ErrorCode;

@WebServlet("/Cart")
public class CartServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			int bid = Integer.parseInt(req.getParameter("bookId"));
			int uid = Integer.parseInt(req.getParameter("userId"));
			int quantity = 1;
			if (req.getParameter("quantity") != null) {
				quantity = Integer.parseInt(req.getParameter("quantity"));
			}

			// Action parameter: "add" (default) or "update"
			String action = req.getParameter("action") != null ? req.getParameter("action") : "add";

			CartDao cartDao = new CartDao(DBConnect.getCon());
			Cart existingCart = cartDao.getCartItemByUserAndBook(uid, bid);

			BookDao dao = new BookDao(DBConnect.getCon());
			Books b = dao.getBookById(bid);

			boolean success;
			if (existingCart != null) {
				if ("update".equalsIgnoreCase(action)) {
					// Directly update to given quantity (from checkout.jsp editable field)
					int newQuantity = quantity;
					int totalPrice = Integer.parseInt(b.getPrice()) * newQuantity;

					existingCart.setQuantity(newQuantity);
					existingCart.setTotal_price(totalPrice);

					success = cartDao.updateCart(existingCart);

				} else {
					// Default behavior: increment quantity (from Add-to-Cart button)
					int newQuantity = existingCart.getQuantity() + quantity;
					int totalPrice = Integer.parseInt(b.getPrice()) * newQuantity;

					existingCart.setQuantity(newQuantity);
					existingCart.setTotal_price(totalPrice);

					success = cartDao.updateCart(existingCart);
				}
			} else {

				// New book in cart
				Cart c = new Cart();
				c.setBid(bid);
				c.setUid(uid);
				c.setBook_name(b.getBookname());
				c.setAuthor(b.getAuthor());
				c.setQuantity(quantity);
				c.setPrice(Integer.parseInt(b.getPrice()));
				c.setTotal_price(Integer.parseInt(b.getPrice()) * quantity);

				success = cartDao.addToCart(c);
			}
			HttpSession session = req.getSession();

			if (success) {
				if ("update".equalsIgnoreCase(action)) {
					session.setAttribute("cartMsg", "Cart updated successfully!");
					resp.sendRedirect("checkout.jsp");
				} else {

					session.setAttribute("addcart", "Book Added to Cart");
					resp.sendRedirect("allNewbooks.jsp");
				}
			} else {
				session.setAttribute("failed", "Something went wrong");
				resp.sendRedirect("allNewbooks.jsp");

			}
		}

		catch (Exception e) {
			ErrorResponse errorResponse = new ErrorResponse(ErrorCode.USER_NOT_FOUND.getName(),
					ErrorCode.USER_NOT_FOUND.getCode(), ErrorCode.USER_NOT_FOUND.getMessage());
			req.setAttribute("errorResponse", errorResponse);

			// Optional: log full stack trace
			e.printStackTrace();

			// Forward to error JSP
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		}

	}

}
