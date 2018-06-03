package com.lucapiras.snk.user;

import org.springframework.ui.Model;

/**
 *
 * @author Luca Piras
 */
public interface IUserController {
    
    public String save(String username, Model model);
}