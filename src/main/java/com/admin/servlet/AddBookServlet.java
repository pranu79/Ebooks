package com.admin.servlet;

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
import com.entity.Books;

@WebServlet("/admin/addBook")
@MultipartConfig
public class AddBookServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		try {

			String bookname = req.getParameter("bookname");
			String author = req.getParameter("authorname");
			String price = req.getParameter("price");
			String categories = req.getParameter("btype");
			String status = req.getParameter("bstatus");
			Part part = req.getPart("imgs");
			String originalFileName = part.getSubmittedFileName();
			

			Books b = new Books(bookname, author, price, categories, status, originalFileName, "admin");

			BookDao dao = new BookDao(DBConnect.getCon());

			boolean f = dao.addBook(b);

			if (f) {

				//local code
			String path = getServletContext().getRealPath("") + "books";
				File folder = new File(path);
				if (!folder.exists())
					folder.mkdirs();
				part.write(path + File.separator + originalFileName);

				session.setAttribute("succMsg", "Book Added Successfully");
				resp.sendRedirect(req.getContextPath() + "/admin/addbook.jsp");
			} else {
				session.setAttribute("errorMsg", "Failed to add book!");
				resp.sendRedirect(req.getContextPath() + "/admin/addbook.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMsg", "Something went wrong" + e.getMessage());
			resp.sendRedirect(req.getContextPath() + "/admin/addbook.jsp");

		}

	}

}
