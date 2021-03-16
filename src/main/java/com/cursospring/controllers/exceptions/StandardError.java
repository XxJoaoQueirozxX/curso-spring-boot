package com.cursospring.controllers.exceptions;

import java.io.Serializable;
import java.time.Instant;

public class StandardError implements Serializable {
    private String error;
    private String message;
    private Integer code;
    private Instant timestamp;
    private String path;

    public StandardError() {
    }

    public StandardError(String error, String message, Integer code, Instant timestamp, String path) {
        this.error = error;
        this.message = message;
        this.code = code;
        this.timestamp = timestamp;
        this.path = path;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
