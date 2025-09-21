package com.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDao;
import com.db.DBConnect;
import com.entity.User;


@WebServlet("/addAddress")
public class AddAddress extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			int id = Integer.parseInt(req.getParameter("id"));
			String address = req.getParameter("address");
			String landmark = req.getParameter("landmark");
			String state = req.getParameter("state");
			String city = req.getParameter("city");
			String zip = req.getParameter("zip");
			

			HttpSession session = req.getSession();			

			UserDao dao = new UserDao(DBConnect.getCon());
			User u= dao.getUserById(id);
			
			u.setAddress(address);
			u.setLandmark(landmark);
			u.setState(state);
			u.setCity(city);
			u.setPincode(zip);
			
			boolean f = dao.updateAddress(u);

			if (f) {
				User updatedUser= dao.getUserById(id);
				session.setAttribute("userobj", updatedUser);

				session.setAttribute("succMsg", "Address added Successfully");
				resp.sendRedirect("addAddress.jsp");

			} else {

				session.setAttribute("errorMsg", "Something went wrong");
				resp.sendRedirect("addAddress.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
