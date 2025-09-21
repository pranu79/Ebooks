package com.user.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.util.AppConstants;

@WebServlet("/createRazorpayOrder")
public class CreateRazorpayOrder extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String amountStr = request.getParameter("amount"); // from checkout.jsp
            int amount = Integer.parseInt(amountStr) * 100; // amount in paise

            // Create Razorpay client
            RazorpayClient razorpay = new RazorpayClient(AppConstants.RAZORPAY_KEY, AppConstants.RAZORPAY_SECRET);

            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", amount);
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "txn_" + System.currentTimeMillis());

            // Create order
            Order order = razorpay.orders.create(orderRequest);

            // Return order_id to frontend
            response.setContentType("application/json");
            response.getWriter().write(order.toString());

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error creating Razorpay order");
        }
    }
}

