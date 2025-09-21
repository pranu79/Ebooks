package com.admin.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BookDao;
import com.db.DBConnect;
import com.entity.Books;

@WebServlet("/admin/searchBook")
public class AdminSearchBookServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("searching.");
		String keyword = req.getParameter("keyword");
		List<Books> books = new ArrayList<>();

		BookDao dao = new BookDao(DBConnect.getCon());

			if (keyword == null || keyword.trim().isEmpty()) {
				books= dao.getAllBooks();
			}
			else {
				books = dao.getBookBySearch(keyword);
			}


		req.setAttribute("bookList", books);
		RequestDispatcher rd = req.getRequestDispatcher("/admin/allbooks.jsp");
		rd.forward(req, resp);
	}
}
