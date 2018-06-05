package com.lucapiras.snk.utils.dispatcher;

/**
 *
 * @author Luca Piras
 */
public interface IDispatcher {
    
    public void dispatch(String request) throws Exception;
}