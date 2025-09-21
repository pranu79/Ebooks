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
import com.entity.Books;



@WebServlet("/admin/editBook")
public class EditBook extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			String bookname = req.getParameter("bookname");
			String author = req.getParameter("authorname");
			String price = req.getParameter("price");
			String status = req.getParameter("bstatus");
			int id = Integer.parseInt(req.getParameter("id"));

			Books b = new Books();
			b.setBookId(id);
			b.setBookname(bookname);
			b.setAuthor(author);
			b.setPrice(price);
			b.setStatus(status);

			HttpSession session = req.getSession();

			BookDao dao = new BookDao(DBConnect.getCon());

			boolean f = dao.updateBook(b);

			if (f) {
				session.setAttribute("succMsg", "Book Details Updated Successfully");
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
