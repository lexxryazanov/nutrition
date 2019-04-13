package com.nexing.nutrition.database.repository;

import com.nexing.nutrition.database.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
