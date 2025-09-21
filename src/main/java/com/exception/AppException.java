package com.exception;

import com.util.ErrorCode;

public class AppException extends RuntimeException {
    private final ErrorCode errorCode;

    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage()); // pass detailed message to RuntimeException
        this.errorCode = errorCode;
    }
    

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getCode() {
        return errorCode.getCode();
    }

    public String getName() {
        return errorCode.getName();
    }

    public String getMessage() {
        return errorCode.getMessage();
    }
}
