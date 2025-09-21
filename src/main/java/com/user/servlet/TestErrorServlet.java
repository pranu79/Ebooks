
package com.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.ErrorResponse;
import com.util.ErrorCode;

@WebServlet("/testError")
public class TestErrorServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Manually create an error response
		ErrorResponse err = new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR);
		

		// Set in request scope
		req.setAttribute("errorResponse", err);
        System.out.println("✅ Setting errorResponse: " + err); // Debug log


		// Forward to error.jsp
		req.getRequestDispatcher("error.jsp").forward(req, resp);
	}
}
