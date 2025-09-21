package com.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class RazorpaySignatureVerifier {

    public static boolean verify(String orderId, String paymentId, String razorpaySignature) {
        try {
            String data = orderId + "|" + paymentId;

            SecretKeySpec secretKeySpec = new SecretKeySpec(AppConstants.RAZORPAY_SECRET.getBytes(), AppConstants.HMAC_SHA256);
            Mac mac = Mac.getInstance(AppConstants.HMAC_SHA256);
            mac.init(secretKeySpec);

            byte[] hashBytes = mac.doFinal(data.getBytes());
            // Convert to HEX
            StringBuilder hashHex = new StringBuilder();
            for (byte b : hashBytes) {
                hashHex.append(String.format("%02x", b));
            }

            String generatedSignature = hashHex.toString();
            return generatedSignature.equals(razorpaySignature);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

