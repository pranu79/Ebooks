package com.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.dao.AdminDao;
import com.db.DBConnect;
import com.entity.Admin;

@WebServlet("/admin/login")
public class AdminLogin extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		String email = req.getParameter("email");
		String password = req.getParameter("password");

		AdminDao adminDao = new AdminDao(DBConnect.getCon());
		Admin admin = adminDao.getAdminByUsername(email);

		if (admin != null && BCrypt.checkpw(password, admin.getPassword())) {
			session.setAttribute("adminobj", admin);
			//session.setMaxInactiveInterval(30 * 60);
			resp.sendRedirect(req.getContextPath() + "/admin/home.jsp");
		} else {
			session.setAttribute("errorMsg", "Invalid username or password!");
			resp.sendRedirect(req.getContextPath() + "/admin/login.jsp");

		}

	}

}
