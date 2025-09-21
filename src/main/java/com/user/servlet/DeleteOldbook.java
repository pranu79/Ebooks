package com.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.BookDao;
import com.db.DBConnect;
import com.dto.ErrorResponse;
import com.util.ErrorCode;

@WebServlet("/deleteOldbook")
public class DeleteOldbook extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			int bookId = Integer.parseInt(req.getParameter("bookId"));
			String email = req.getParameter("email");

			HttpSession session = req.getSession();

			BookDao dao = new BookDao(DBConnect.getCon());
			boolean f = dao.deleteUserBook(bookId, email);

			if (f) {

				session.setAttribute("succMsg", "Book Deleted Successfully");
				resp.sendRedirect("oldBooks.jsp");
			} else {
				session.setAttribute("errorMsg", "Something went wrong");
				resp.sendRedirect("oldBooks.jsp");
			}

		} catch (Exception e) {
			ErrorResponse errorResponse = new ErrorResponse(ErrorCode.USER_NOT_FOUND.getName(),
					ErrorCode.USER_NOT_FOUND.getCode(),ErrorCode.USER_NOT_FOUND.getMessage());
			req.setAttribute("errorResponse", errorResponse);

			// Optional: log full stack trace
			e.printStackTrace();

			// Forward to error JSP
			req.getRequestDispatcher("error.jsp").forward(req, resp);
			e.printStackTrace();
		}
	}

}
