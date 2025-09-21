package com.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AdminLogout")
public class AdminLogout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        try {
            HttpSession session = req.getSession(false); // get existing session, don’t create new

            if (session != null && session.getAttribute("adminobj") != null) {
                session.invalidate(); // destroy old session

                // ✅ Create fresh session for success message
                HttpSession newSession = req.getSession(true);
                newSession.setAttribute("succMsg", "Logout Successfully");
            } 
                resp.sendRedirect(req.getContextPath() + "/admin/login.jsp"); // if no session, just go to login
            
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/error.jsp"); // fallback
        }
    }
}
