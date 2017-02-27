/*
 * @(#)NoPropertyException.java
 *
 * Copyright (c) 2014 The Company Company. All Rights Reserved.
 */


package com.core.context;

/**
 * Signals that mandatory property is absent in context.
 *
 */
public class NoPropertyException extends Exception {

    /**
     * Default constructor.
     */
    public NoPropertyException() {
    }

    /**
     * Constructor with message.
     * @param message message.
     */
    public NoPropertyException(String message) {
        super(message);
    }

    /**
     * Constructor with cause.
     * @param cause exception cause.
     */
    public NoPropertyException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor with message and cause.
     * @param message message.
     * @param cause cause.
     */
    public NoPropertyException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor.
     * @param message message.
     * @param cause cause.
     * @param enableSuppression enable suppersiion of exception.
     * @param writableStackTrace enable stack trace writing.
     */
    public NoPropertyException(
        String message,
        Throwable cause,
        boolean enableSuppression,
        boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
