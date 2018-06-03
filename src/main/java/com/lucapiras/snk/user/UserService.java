package com.lucapiras.snk.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Luca Piras
 */
@Service
public class UserService implements IUserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    protected UserRepository repository;
    
    @Override
    public User save(User user) {
        User saved = repository.save(user);
        
        if (logger.isDebugEnabled()) {
            logger.debug("Users found with findAll():");
            logger.debug("-------------------------------");
            for (User current : repository.findAll()) {
                logger.debug(current.toString());
            }
            logger.debug("");
        }
        
        return saved;
    }
}