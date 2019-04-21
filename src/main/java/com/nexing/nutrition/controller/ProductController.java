package com.nexing.nutrition.controller;

import com.nexing.nutrition.controller.exception.ResourceNotFoundException;
import com.nexing.nutrition.database.entity.Product;
import com.nexing.nutrition.database.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(path = "/api/product")
    public @ResponseBody
    Iterable<Product> getProducts(@RequestParam(name = "keyword", required = false) String keyword, @PageableDefaults(value = Integer.MAX_VALUE) Pageable pageable) {
        if (keyword == null || keyword.length() == 0) {
            return productRepository.findAll(pageable).getContent();
        } else {
            return productRepository.findAllByKeyword(keyword, pageable).getContent();
        }
    }

    @GetMapping(path = "/api/product/{id}")
    public @ResponseBody
    Product getProduct(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product doesn't exist. Id=" + id));
    }
}
