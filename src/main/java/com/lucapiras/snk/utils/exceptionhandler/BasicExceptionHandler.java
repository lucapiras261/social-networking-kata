package com.lucapiras.snk.utils.exceptionhandler;

import com.lucapiras.snk.exception.UnknownRequestException;
import com.lucapiras.snk.utils.viewresolver.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BasicExceptionHandler implements IExceptionHandler {

    @Autowired
    protected IView unknownRequestView;

    @Override
    public void handle(Exception ex) throws Exception {

        if (ex instanceof UnknownRequestException) {
            unknownRequestView.show(null);
        } else {
            throw ex;
        }
    }

}