package com.lucapiras.dispatcher;

import com.lucapiras.exception.ExitException;
import com.lucapiras.exception.UnknownRequestException;

/**
 *
 * @author Luca Piras
 */
public interface IDispatcher {
    
    public void dispatch(String request) throws ExitException, UnknownRequestException;
}