package com.instana.assignment.exception;

public class NoSuchTraceException extends RuntimeException {
    public NoSuchTraceException(String msg){
        super(msg);
    }
}
