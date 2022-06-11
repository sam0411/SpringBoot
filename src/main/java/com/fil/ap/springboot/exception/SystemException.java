package com.fil.ap.springboot.exception;

public class SystemException extends RuntimeException {

    private String code;

    public SystemException(String code, String message) {

        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
