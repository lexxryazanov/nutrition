package com.nexing.nutrition.controller;

import com.nexing.nutrition.database.entity.Product;
import com.nexing.nutrition.database.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(path = "/api/product")
    public @ResponseBody
    Iterable getProducts(@RequestParam(name = "keyword", required = false) String keyword, @RequestParam(name = "page", defaultValue = "0") Integer page, @RequestParam(name = "size", defaultValue = "100") Integer size) {
        if (keyword == null || keyword.length() == 0) {
            return productRepository.findAll(PageRequest.of(page, size)).getContent();
        } else {
            return productRepository.findAllByKeyword(keyword, PageRequest.of(page, size)).getContent();
        }
    }

    @GetMapping(path = "/api/product/{id}")
    public @ResponseBody
    Optional<Product> getProduct(@PathVariable(value = "id")  Integer id) {
        return productRepository.findById(id);
    }
}
