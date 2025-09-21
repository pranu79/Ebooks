package com.user.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.dao.BookDao;
import com.db.DBConnect;
import com.dto.ErrorResponse;
import com.entity.Books;
import com.util.ErrorCode;

@WebServlet("/addOldbook")
@MultipartConfig
public class AddOldBook extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		try {

			String bookname = req.getParameter("bookname");
			String author = req.getParameter("authorname");
			String price = req.getParameter("price");
			String categories = "Old";
			String status = "Active";
			Part part = req.getPart("imgs");
			String filename = part.getSubmittedFileName();
			String email = req.getParameter("email");

			Books b = new Books(bookname, author, price, categories, status, filename, email);

			BookDao dao = new BookDao(DBConnect.getCon());

			boolean f = dao.addBook(b);

			if (f) {

				String path = getServletContext().getRealPath("") + "books";
				File file = new File(path);
				part.write(path + File.separator + filename);

				session.setAttribute("succMsg", "Book Added Successfully");
				resp.sendRedirect("sellBook.jsp");
			} else {
				session.setAttribute("errorMsg", "Something went wrong");
				resp.sendRedirect("sellBook.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// Create error response
			ErrorResponse errorResponse = new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR.getName(),
					ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "Failed to add old book: " + e.getMessage());

			req.setAttribute("errorResponse", errorResponse);

			// Forward to error.jsp
			req.getRequestDispatcher("error.jsp").forward(req, resp);

		}

	}

}
