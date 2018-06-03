package com.lucapiras.snk.utils.domain.helper;

import com.lucapiras.snk.post.Post;
import com.lucapiras.snk.user.User;

/**
 *
 * @author Luca Piras
 */
public interface IDomainHelper {
    
    public User createCharlie();
    public User createBob();
    public User createAlice();
    
    public Post createFirstPostCharlie();
    
    public Post createFirstPostBob();    
    public Post createSecondPostBob();
    
    public Post createFirstPostAlice();
}