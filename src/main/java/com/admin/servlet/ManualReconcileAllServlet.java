package com.admin.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/admin/reconcileAll")
public class ManualReconcileAllServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		try {
			ReconciliationTask task = new ReconciliationTask();
			task.run(); // Run immediately
			session.setAttribute("succMsg", "All pending orders reconciled successfully.");
		} catch (Exception e) {
			session.setAttribute("errorMsg", "Error: " + e.getMessage());
			e.printStackTrace();
		}
		resp.sendRedirect(req.getContextPath() + "/admin/reconcile.jsp");
	}
}
