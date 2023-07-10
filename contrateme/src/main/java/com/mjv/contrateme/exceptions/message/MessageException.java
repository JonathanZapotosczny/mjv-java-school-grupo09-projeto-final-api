package com.mjv.contrateme.exceptions.message;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class MessageException {

    private Date timestamp;
    private int status;
    private String error;
    private List<String> message;
    private String path;

    public MessageException(Date timestamp, int status, String error, List<String> message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public MessageException(Date timestamp, int status, String error, String messageS, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = Arrays.asList(messageS);
        this.path = path;
    }
}