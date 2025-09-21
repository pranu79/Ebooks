package com.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.BookDao;
import com.db.DBConnect;

@WebServlet("/admin/deleteBook")
public class DeleteBook extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			int bookId = Integer.parseInt(req.getParameter("bookId"));

			HttpSession session = req.getSession();

			BookDao dao = new BookDao(DBConnect.getCon());

			boolean f = dao.deleteBook(bookId);

			if (f) {
				session.setAttribute("succMsg", "Book Details Deleted Successfully");
				resp.sendRedirect(req.getContextPath() + "/admin/allbooks.jsp");
			} else {
				session.setAttribute("errorMsg", "Something Went Wrong");
				resp.sendRedirect(req.getContextPath() + "/admin/allbooks.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
