package com.nexing.nutrition.database.repository;

import com.nexing.nutrition.database.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
