package com.nexing.nutrition.controller;

import com.nexing.nutrition.database.entity.Product;
import com.nexing.nutrition.database.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(path = "/product")
    public @ResponseBody
    Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
