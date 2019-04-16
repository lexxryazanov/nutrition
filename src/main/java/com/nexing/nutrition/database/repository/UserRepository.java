package com.nexing.nutrition.database.repository;

import com.nexing.nutrition.database.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("select u from User u where u.name = ?1")
    User findByUsername(String username);
}
