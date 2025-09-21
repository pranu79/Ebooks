package com.util;

public enum ErrorCode {
    INVALID_REQUEST("ERR001", "Invalid Request", "The request data is invalid or missing."),
    PAYMENT_FAILED("ERR002", "Payment Failed", "There was a problem processing your payment. Please try again."),
    PENDING("ERROR3","⏳ Payment Pending","Your payment is being processed. Please wait for confirmation"),
    USER_NOT_FOUND("ERR004", "User Not Found", "The requested user does not exist."),
    ORDER_NOT_FOUND("ERR005", "Order Not Found", "The requested order could not be found."),
    ORDER_FAILED("ERR005","Payment Verification Failed!","Unfortunately, we could not verify your payment. Please try\r\n"
    		+ "	again or contact support."),
    INTERNAL_SERVER_ERROR("ERR006", "Internal Server Error", "An unexpected error occurred. Please try again later.");

    private final String code;
    private final String name;
    private final String message;

    ErrorCode(String code, String name, String message) {
        this.code = code;
        this.name = name;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}
