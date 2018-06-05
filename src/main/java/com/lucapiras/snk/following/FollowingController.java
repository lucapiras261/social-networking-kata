package com.lucapiras.snk.following;

import com.lucapiras.snk.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

/**
 *
 * @author Luca Piras
 */
@Controller
public class FollowingController implements IFollowingController {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    protected IFollowingService service;
    
    @Override
    public String save(String usernameFollower, String usernameFollowed, 
                       Model model) throws FollowingViolationException {
        
        User follower = new User(usernameFollower);
        User followed = new User(usernameFollowed);
        
        Following following = new Following(new FollowingId(follower, followed));
        
        service.save(following);
        
        return "empty";
    }
}