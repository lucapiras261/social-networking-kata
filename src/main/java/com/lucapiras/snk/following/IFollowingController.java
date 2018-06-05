package com.lucapiras.snk.following;

import org.springframework.ui.Model;

/**
 *
 * @author Luca Piras
 */
public interface IFollowingController {
    
    public String save(String usernameFollower, String usernameFollowed, 
                       Model model) throws FollowingViolationException;
    
}