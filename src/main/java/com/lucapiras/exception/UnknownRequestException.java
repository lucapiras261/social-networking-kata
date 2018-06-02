package com.lucapiras.exception;

/**
 *
 * @author Luca Piras
 */
public class UnknownRequestException extends Exception {

    /**
     * Creates a new instance of <code>UnknownRequestException</code> without
     * detail message.
     */
    public UnknownRequestException() {
    }

    /**
     * Constructs an instance of <code>UnknownRequestException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UnknownRequestException(String msg) {
        super(msg);
    }
}