package com.brabos.bahia.instagram.test.resources.exceptions;

import java.io.Serializable;
import java.util.Date;

public class StandardError implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date timestamp;
    private String message;
    private String details;
    private int code;

    public StandardError() {
    }

    public StandardError(Date timestamp, String message, String details, int code) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.code = code;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public int getCode() {
        return code;
    }
}
