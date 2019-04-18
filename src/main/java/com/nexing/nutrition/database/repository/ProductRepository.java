package com.nexing.nutrition.database.repository;

import com.nexing.nutrition.database.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    Page<Product> findAll(Pageable pageable);

    @Query(value = "select p from Product p inner join Company c on p.company = c.id where lower(p.name) like lower(concat('%',:keyword,'%')) or lower(c.name) like lower(concat('%',:keyword,'%'))")
    Page<Product> findAllByKeyword(String keyword, Pageable pageable);
}
