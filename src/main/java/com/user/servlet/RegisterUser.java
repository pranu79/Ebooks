package com.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.dao.UserDao;
import com.db.DBConnect;
import com.entity.User;

@WebServlet("/register")
public class RegisterUser extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			String name = req.getParameter("name");
			String email = req.getParameter("email");
			String phno = req.getParameter("phno");
			String password = req.getParameter("password");
			String check = req.getParameter("check");

			//hash password before saving in db
			String hashedPwd = BCrypt.hashpw(password, BCrypt.gensalt(12));
			
			User u = new User();
			u.setName(name);
			u.setEmail(email);
			u.setPhno(phno);
			u.setPassword(hashedPwd);

			HttpSession session = req.getSession();

			if (check != null) {

				UserDao dao = new UserDao(DBConnect.getCon());
				boolean exists = dao.checkEmail(email);

				if (!exists) {

					boolean f = dao.userRegister(u);

					if (f) {

						session.setAttribute("succMsg", "User Register Successfully");
						resp.sendRedirect("register.jsp");

					} else {

						session.setAttribute("errorMsg", "Something went wrong");
						resp.sendRedirect("register.jsp");

					}

				} else {
					session.setAttribute("errorMsg", "Email Id Already Exist Try Another Email Id");
					resp.sendRedirect("register.jsp");
				}

			} else {
				session.setAttribute("errorMsg", "Please Check Agree & Terms Condition");
				resp.sendRedirect("register.jsp");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
