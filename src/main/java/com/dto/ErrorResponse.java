package com.dto;

import com.exception.AppException;
import com.util.ErrorCode;

public class ErrorResponse {
    private String name;
    private String code;
    private String message;

    public ErrorResponse(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.name = errorCode.name();
        this.message = errorCode.getMessage();
    }

    public ErrorResponse(AppException ex) {
        this(ex.getErrorCode());
    }

	

    public ErrorResponse(String name, String code, String message) {
        this.name = name;
        this.code = code;
        this.message = message;
    }

    // --- getters and setters ---
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // For debugging (optional)
    @Override
    public String toString() {
        return "ErrorResponse{name='" + name + "', code='" + code + "', message='" + message + "'}";
    }
}
