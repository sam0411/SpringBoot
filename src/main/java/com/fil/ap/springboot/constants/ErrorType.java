package com.fil.ap.springboot.constants;

public enum ErrorType {

    REQUEST_INVALID_INPUT("601", "Invalid Input Parameter / Method Type / Media Type"),

    SYSTEM_ERROR("500", "System Error");
    private String code;

    private String message;

    private ErrorType(String code, String message) {

        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
