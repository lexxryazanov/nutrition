package com.nexing.nutrition.database.repository;

import com.nexing.nutrition.database.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    @Query(value = "select p.id, p.name, c.name from Product p inner join Company c on p.company = c.id")
    Page findAll(Pageable pageable);

    @Query(value = "select p.id, p.name, c.name from Product p inner join Company c on p.company = c.id where lower(p.name) like lower(concat('%',:keyword,'%')) or lower(c.name) like lower(concat('%',:keyword,'%'))")
    Page findAllByKeyword(String keyword, Pageable pageable);
}
