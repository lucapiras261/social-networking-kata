package com.lucapiras.snk.user;


import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Luca Piras
 */
public interface UserRepository extends CrudRepository<User, String> {
}