package com.instana.assignment.exception;

/**
 * This represents the exception when no trace is found
 */
public class NoSuchTraceException extends RuntimeException {
    public NoSuchTraceException(String msg){
        super(msg);
    }
}
