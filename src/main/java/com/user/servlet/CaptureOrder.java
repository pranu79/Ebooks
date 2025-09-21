package com.user.servlet;

import java.io.IOException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Hex;
import org.json.JSONObject;

import com.dao.CartDao;
import com.dao.OrderDao;
import com.db.DBConnect;
import com.dto.ErrorResponse;
import com.entity.User;
import com.exception.AppException;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.util.AppConstants;
import com.util.ErrorCode;

@WebServlet("/CaptureOrder")
public class CaptureOrder extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		try {
			String razorpay_orderId = req.getParameter("razorpay_order_id");
			String paymentId = req.getParameter("razorpay_payment_id");
			String signature = req.getParameter("razorpay_signature");
			String orderId = (String) session.getAttribute("orderId");
			User u = (User) session.getAttribute("userobj");

			JSONObject orderRequest = new JSONObject();
			orderRequest.put("razorpay_payment_id", paymentId);
			orderRequest.put("razorpay_order_id", razorpay_orderId);
			orderRequest.put("razorpay_signature", signature);

			OrderDao dao = new OrderDao(DBConnect.getCon());

			if (verifySignature(razorpay_orderId, paymentId, signature)) {
				dao.updateOrder(orderId, AppConstants.STATUS_PENDING, razorpay_orderId, paymentId, signature);
				CartDao cartDao = new CartDao(DBConnect.getCon());
				cartDao.deleteCart(u.getId());

				// 3️⃣ Call Razorpay API for reconciliation (real status)
				RazorpayClient client = new RazorpayClient(AppConstants.RAZORPAY_KEY, AppConstants.RAZORPAY_SECRET);
				Payment payment = client.payments.fetch(paymentId);
				String actualStatus = payment.get("status"); // e.g., "captured", "failed"

				if ("captured".equalsIgnoreCase(actualStatus)) {
					dao.updatedOrderStatusByPaymentId(paymentId, AppConstants.STATUS_SUCCESS);
					resp.sendRedirect("orderSuccess.jsp");
				} else if ("failed".equalsIgnoreCase(actualStatus)) {
					dao.updatedOrderStatusByPaymentId(paymentId, AppConstants.STATUS_FAILED);
					throw new AppException(ErrorCode.ORDER_FAILED);
				} else {
					dao.updatedOrderStatusByPaymentId(paymentId, AppConstants.STATUS_PENDING);
					throw new AppException(ErrorCode.PENDING);
				}

			} else {
				dao.updateOrder(orderId, AppConstants.STATUS_FAILED, razorpay_orderId, paymentId, signature);
				throw new AppException(ErrorCode.PAYMENT_FAILED);

			}

		} catch (AppException e) {
			e.printStackTrace();
			
			req.setAttribute("errorResponse", new ErrorResponse(e));
			// Forward to error.jsp
			req.getRequestDispatcher("error.jsp").forward(req, resp);

		} catch (Exception ex) {
			// Handle all other unexpected exceptions
			ErrorResponse errorResponse = new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR);
			req.setAttribute("errorResponse", errorResponse);

			// Optional: log full stack trace
			ex.printStackTrace();

			// Forward to error JSP
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		}

	}

	private boolean verifySignature(String orderId, String paymentId, String signature) {
		try {
			String payload = orderId + "|" + paymentId;
			String expectedSignature = hmacSHA256(payload, AppConstants.RAZORPAY_SECRET);
			return expectedSignature.equals(signature);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	private String hmacSHA256(String data, String secret) {
		try {
			Mac mac = Mac.getInstance(AppConstants.HMAC_SHA256);
			SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), AppConstants.HMAC_SHA256);
			mac.init(secretKeySpec);
			byte[] hash = mac.doFinal(data.getBytes());
			return new String(Hex.encodeHex(hash));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
