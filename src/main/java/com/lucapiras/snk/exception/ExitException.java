package com.lucapiras.snk.exception;

/**
 * Exception thrown when it is needed to conclude an operation or to exit from
 * the entire execution of the program
 * 
 * @author Luca Piras
 */
public class ExitException extends Exception {

    /**
     * Creates a new instance of <code>ExitException</code> without detail
     * message.
     */
    public ExitException() {
    }

    /**
     * Constructs an instance of <code>ExitException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public ExitException(String msg) {
        super(msg);
    }
}