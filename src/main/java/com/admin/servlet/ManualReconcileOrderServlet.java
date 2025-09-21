package com.admin.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.OrderDao;
import com.db.DBConnect;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.util.AppConstants;

@WebServlet("/admin/reconcileOrder")
public class ManualReconcileOrderServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String paymentId = req.getParameter("paymentId");
		HttpSession session = req.getSession();
		try {
			RazorpayClient client = new RazorpayClient(AppConstants.RAZORPAY_KEY, AppConstants.RAZORPAY_SECRET);
			OrderDao dao = new OrderDao(DBConnect.getCon());

			Payment payment = client.payments.fetch(paymentId);
			String razorpayStatus = payment.get("status"); // e.g. captured, failed
			String  mappedStatus= ReconciliationTask.mapRazorpayStatus(razorpayStatus);
			boolean updated = dao.updatedOrderStatusByPaymentId(paymentId, mappedStatus);

			if (updated) {
				session.setAttribute("succMsg", "Order reconciled successfully. Status = " + mappedStatus);
				resp.sendRedirect(req.getContextPath() + "/admin/reconcile.jsp");

			} else {
				session.setAttribute("errorMsg", "Failed to update order. Check DB entry.");
				resp.sendRedirect(req.getContextPath() + "/admin/reconcile.jsp");

			}
		} catch (Exception e) {
			session.setAttribute("errorMsg", "Error: " + e.getMessage());
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/admin/reconcile.jsp");
		}
	}
}
