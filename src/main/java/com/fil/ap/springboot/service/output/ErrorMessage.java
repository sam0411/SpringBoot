package com.fil.ap.springboot.service.output;

import java.io.Serializable;

public class ErrorMessage implements Serializable {

    private String code;

    private String message;

    public ErrorMessage() {

    }

    public ErrorMessage(String code, String message) {

        this.code = code;
        this.message = message;
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
}
