package com.admin.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BookDao;
import com.db.DBConnect;
import com.entity.Books;

@WebServlet("/viewImg")
@MultipartConfig
public class GetImageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		int id = Integer.parseInt(req.getParameter("bookId"));

		BookDao dao = new BookDao(DBConnect.getCon());
		Books book = dao.getBookById(id);

		if (book != null && book.getImageData() != null) {
			

			// Detect MIME type from file extension
			String fileName = book.getPhotoName();
			String contentType = getServletContext().getMimeType(fileName);

			if (contentType == null) {
				// fallback if unknown
				contentType = "application/octet-stream";
			}

			resp.setContentType(contentType);
			resp.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\"");
			try (OutputStream out = resp.getOutputStream()) {
				out.write(book.getImageData());
			}
		} else {

			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Image not found for Id" + id);
		}

	}

}
