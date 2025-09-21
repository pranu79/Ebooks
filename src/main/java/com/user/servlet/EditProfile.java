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

@WebServlet("/editProfile")
public class EditProfile extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("userobj");
		try {

			int id = Integer.parseInt(req.getParameter("uid"));
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			String phno = req.getParameter("phno");
			String password = req.getParameter("password");
			String newPassword = req.getParameter("newpassword");

			UserDao dao = new UserDao(DBConnect.getCon());

			boolean f = dao.checkPassword(id, password);

			if (f) {
				// update user details
				User u = new User();
				u.setId(id);
				u.setName(name);
				u.setEmail(email);
				u.setPhno(phno);

				// if new password entered → hash & save
				if (newPassword != null && !newPassword.trim().isEmpty()) {
					String hashedNew = BCrypt.hashpw(newPassword, BCrypt.gensalt(12));
					u.setPassword(hashedNew);
				} else {
					// else keep old password from session user
					u.setPassword(user.getPassword());
				}

				boolean f2 = dao.editProfile(u);

				if (f2) {
					user.setName(name);
					user.setEmail(email);
					user.setPhno(phno);
					if (newPassword != null && !newPassword.trim().isEmpty()) {
						user.setPassword(u.getPassword()); // update password in session
					}
					session.setAttribute("userobj", user);
					session.setAttribute("succMsg", "Profile Updated Successfully");
				} else {
					session.setAttribute("errorMsg", "Something Went Wrong");
				}

			}

			else {
				session.setAttribute("errorMsg", "Your Password is Incorrect");
			}
			resp.sendRedirect("editProfile.jsp");

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMsg", "Server Error: " + e.getMessage());
			resp.sendRedirect("editProfile.jsp");
		}
	}

}
