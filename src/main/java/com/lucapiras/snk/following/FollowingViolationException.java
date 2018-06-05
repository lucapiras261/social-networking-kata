package com.lucapiras.snk.following;

/**
 *
 * @author Luca Piras
 */
public class FollowingViolationException extends Exception {

    /**
     * Creates a new instance of <code>FollowingViolationException</code>
     * without detail message.
     */
    public FollowingViolationException() {
    }

    /**
     * Constructs an instance of <code>FollowingViolationException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public FollowingViolationException(String msg) {
        super(msg);
    }
}
