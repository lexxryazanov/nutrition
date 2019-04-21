package com.nexing.nutrition.database.repository;

import com.nexing.nutrition.database.entity.Company;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CompanyRepository extends CrudRepository<Company, Integer> {

    Optional<Company> findFirstByName(String name);
}
