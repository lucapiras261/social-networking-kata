package com.lucapiras.snk.following;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Luca Piras
 */
@Service
public class FollowingService implements IFollowingService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    protected FollowingRepository repository;
    
    @Override
    public Following save(Following following) {
        Following saved = repository.save(following);
        
        if (logger.isDebugEnabled()) {
            logger.debug("Following found with findAll():");
            logger.debug("-------------------------------");
            for (Following current : repository.findAll()) {
                logger.debug(current.toString());
            }
            logger.debug("");
        }
        
        return saved;
    }
    
}