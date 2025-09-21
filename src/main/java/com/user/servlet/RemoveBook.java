package com.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.CartDao;
import com.db.DBConnect;

@WebServlet("/removeBook")
public class RemoveBook extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			int bid = Integer.parseInt(req.getParameter("bid"));
			int uid = Integer.parseInt(req.getParameter("uid"));
			int cid = Integer.parseInt(req.getParameter("cid"));

			HttpSession session = req.getSession();

			CartDao dao = new CartDao(DBConnect.getCon());
			boolean f = dao.removeBook(bid, uid, cid);

			if (f) {
				session.setAttribute("cartMsg", "Book removed from Cart Successfully");
				resp.sendRedirect("checkout.jsp");
			} else {
				session.setAttribute("failedMsg", "Something Went Wrong");
				resp.sendRedirect("checkout.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
