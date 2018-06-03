package com.lucapiras.snk.dispatcher;

import com.lucapiras.snk.exception.ExitException;
import com.lucapiras.snk.exception.UnknownRequestException;

/**
 *
 * @author Luca Piras
 */
public interface IDispatcher {
    
    public void dispatch(String request) throws ExitException, UnknownRequestException;
}