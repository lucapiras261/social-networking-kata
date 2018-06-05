package com.lucapiras.snk.utils.exceptionhandler;

import com.lucapiras.snk.following.FollowingViolationException;
import com.lucapiras.snk.post.PostingViolationException;
import com.lucapiras.snk.utils.dispatcher.UnknownRequestException;
import com.lucapiras.snk.utils.viewresolver.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BasicExceptionHandler implements IExceptionHandler {

    @Autowired
    protected IView unknownRequestView;
    
    @Autowired
    protected IView postingViolationView;

    @Autowired
    protected IView followingViolationView;
    
            
    @Override
    public void handle(Exception ex) throws Exception {

        if (ex instanceof UnknownRequestException) {
            unknownRequestView.show(null);
        } else if (ex instanceof PostingViolationException) {
            postingViolationView.show(null);
        } else if (ex instanceof FollowingViolationException) {
            followingViolationView.show(null);
        } else {
            throw ex;
        }
    }

}