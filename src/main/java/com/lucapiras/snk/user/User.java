package com.lucapiras.snk.user;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Luca Piras
 */
@Entity
public class User implements Serializable {

    @Id
    @Column(nullable = false)
    protected String username;

    protected User() {}
    
    public User(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return String.format(
                "User[username='%s']", username);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}