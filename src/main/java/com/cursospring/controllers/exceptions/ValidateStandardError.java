package com.cursospring.controllers.exceptions;

import java.time.Instant;
import java.util.List;

public class ValidateStandardError {

    private String message;
    private Integer status;
    private List<String> errors;
    private Instant timestamp;
    private String path;


    public ValidateStandardError() { }

    public ValidateStandardError(String message, Integer status, List<String> errors, Instant timestamp, String path) {
        this.message = message;
        this.status = status;
        this.errors = errors;
        this.timestamp = timestamp;
        this.path = path;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
