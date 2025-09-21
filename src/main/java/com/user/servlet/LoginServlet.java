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
import com.dto.ErrorResponse;
import com.entity.User;
import com.util.ErrorCode;

@WebServlet("/logIn")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserDao dao = new UserDao(DBConnect.getCon());

            String email = req.getParameter("email");
            String password = req.getParameter("password");

            User u = dao.getUserByEmail(email); // ✅ fetch by email only

            if (u != null && BCrypt.checkpw(password, u.getPassword())) {

                // Invalidate old session (prevent fixation)
                HttpSession oldSession = req.getSession(false);
                if (oldSession != null) {
                    oldSession.invalidate();
                }

                // Create new session
                HttpSession session = req.getSession(true);
                session.setAttribute("userobj", u); // ✅ consistent name
                session.setMaxInactiveInterval(30 * 60); // 30 min timeout

                resp.sendRedirect("index.jsp");

            } else {
                // Login failed
                HttpSession session = req.getSession();
                session.setAttribute("errorMsg", "Invalid Email or Password");
                resp.sendRedirect("login.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();

            ErrorResponse errorResponse = new ErrorResponse(
                ErrorCode.USER_NOT_FOUND.getCode(),
                ErrorCode.USER_NOT_FOUND.name(),
                ErrorCode.USER_NOT_FOUND.getMessage()
            );
            req.setAttribute("errorResponse", errorResponse);

            // Forward to error.jsp
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
