package com.admin.servlet;

import java.util.List;
import java.util.TimerTask;
import java.util.logging.Logger;

import com.dao.OrderDao;
import com.db.DBConnect;
import com.entity.Order;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.util.AppConstants;

public class ReconciliationTask extends TimerTask {

    private static final Logger logger = Logger.getLogger(ReconciliationTask.class.getName());

    @Override
    public void run() {
        try {
            logger.info("🚀 Running reconciliation task...");

            RazorpayClient client = new RazorpayClient(AppConstants.RAZORPAY_KEY, AppConstants.RAZORPAY_SECRET);
            OrderDao dao = new OrderDao(DBConnect.getCon());

            // Check PENDING and SUCCESS orders
            String[] statusesToCheck = {AppConstants.STATUS_PENDING, AppConstants.STATUS_SUCCESS};

            for (String status : statusesToCheck) {
                List<Order> orders = dao.getOrderByStatus(status);

                if (orders == null || orders.isEmpty()) {
                    logger.info("ℹ No orders found with status: " + status);
                    continue;
                }

                for (Order o : orders) {
                    String orderId = (o.getOrderid() != null) ? o.getOrderid() : "UNKNOWN";
                    String dbStatus = (o.getStatus() != null) ? o.getStatus() : "UNKNOWN";
                    String paymentId = (o.getRazorpayPaymentId() != null) ? o.getRazorpayPaymentId() : "";

                    if (paymentId.isEmpty()) {
                        logger.warning("⚠ Skipping orderId=" + orderId + " because paymentId is null/empty.");
                        continue;
                    }

                    try {
                        // Fetch payment from Razorpay
                        Payment payment = client.payments.fetch(paymentId);
                        String razorpayStatus = payment.get("status"); // captured, failed, authorized
                        String mappedStatus = mapRazorpayStatus(razorpayStatus);

                        logger.info("Reconciling orderId=" + orderId +
                                ", paymentId=" + paymentId +
                                ", DB status=" + dbStatus +
                                ", Razorpay status=" + razorpayStatus +
                                ", Mapped status=" + mappedStatus);


                        logger.info("Reconciling orderId=" + orderId +
                                ", paymentId=" + paymentId +
                                ", DB status=" + dbStatus +
                                ", Razorpay status=" + razorpayStatus);

                        // Update DB if status differs
                        if (!dbStatus.equalsIgnoreCase(mappedStatus)) {
                            boolean updated = dao.updatedOrderStatusByPaymentId(paymentId, mappedStatus);
                            if (updated) {
                                logger.info("✅ Order " + orderId + " status updated to " + mappedStatus);
                            } else {
                                logger.warning("⚠ Failed to update order " + orderId);
                            }
                        } else {
                            logger.info("ℹ Order " + orderId + " already in sync (" + dbStatus + ")");
                        }

                    } catch (Exception ex) {
                        logger.severe("❌ Error while reconciling orderId=" + orderId +
                                ", paymentId=" + paymentId + ": " + ex.getMessage());
                    }
                }
            }

            logger.info("✅ Reconciliation task completed.");

        } catch (Exception e) {
            logger.severe("❌ Fatal error in reconciliation task: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Map Razorpay payment status to AppConstants status
     */
    static String mapRazorpayStatus(String razorpayStatus) {
        switch (razorpayStatus.toLowerCase()) {
            case "captured":
                return AppConstants.STATUS_SUCCESS;      // Payment confirmed
            case "authorized":
                return AppConstants.STATUS_PENDING;      // Payment pending
            case "failed":
                return AppConstants.STATUS_FAILED;       // Payment failed
            default:
                return AppConstants.STATUS_UNKNOWN;      // Unknown status
        }
    }
}
