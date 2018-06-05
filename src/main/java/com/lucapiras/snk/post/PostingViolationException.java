package com.lucapiras.snk.post;

/**
 *
 * @author Luca Piras
 */
public class PostingViolationException extends Exception {

    /**
     * Creates a new instance of <code>PostingException</code> without detail
     * message.
     */
    public PostingViolationException() {
    }

    /**
     * Constructs an instance of <code>PostingException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PostingViolationException(String msg) {
        super(msg);
    }
}