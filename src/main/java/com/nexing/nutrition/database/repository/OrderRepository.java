package com.nexing.nutrition.database.repository;

import com.nexing.nutrition.database.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {

    Iterable<Order> findAllByUserName(String userName);
}
