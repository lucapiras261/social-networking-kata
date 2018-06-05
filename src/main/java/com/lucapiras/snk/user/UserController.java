package com.lucapiras.snk.user;

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
public class UserController implements IUserController {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    protected IUserService service;
    
    @Override
    public String save(String username, Model model) {
        
        service.save(new User(username));
                
        //model.addAttribute("username", username);
        
        return "empty";
    }
}