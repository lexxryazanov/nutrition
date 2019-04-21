package com.nexing.nutrition.service;

import com.nexing.nutrition.database.entity.Company;
import com.nexing.nutrition.database.entity.Order;
import com.nexing.nutrition.database.entity.OrderState;
import com.nexing.nutrition.database.entity.Product;
import com.nexing.nutrition.database.repository.CompanyRepository;
import com.nexing.nutrition.database.repository.OrderRepository;
import com.nexing.nutrition.database.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private CompanyRepository companyRepository;


    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public Order acceptOrder(Order order) {
        Company company = getOrCreateCompany(order.getCompanyId(), order.getCompanyName());
        Product product = getOrCreateProduct(order.getProductId());

        product.setCompany(company);
        order.apply(product);

        productRepository.save(product);
        order.setState(OrderState.ACCEPTED);
        return orderRepository.save(order);
    }

    private Company getOrCreateCompany(Integer id, String name) {
        if (id == null) {
            Company company = new Company();
            company.setName(name);
            return companyRepository.save(company);
        }

        Company company = companyRepository.findById(id).get();
        if (name != null && !company.getName().equals(name)) {
            company.setName(name);
            return companyRepository.save(company);
        }

        return company;
    }

    private Product getOrCreateProduct(Integer id) {
        return productRepository.findById(id).orElse(new Product());
    }

    @Transactional
    public Order declineOrder(Order order) {
        order.setState(OrderState.DECLINED);
        return orderRepository.save(order);
    }
}
