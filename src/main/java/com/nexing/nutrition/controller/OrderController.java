package com.nexing.nutrition.controller;

import com.nexing.nutrition.controller.exception.OrderIsAlreadyProcessed;
import com.nexing.nutrition.controller.exception.ResourceNotFoundException;
import com.nexing.nutrition.database.entity.Order;
import com.nexing.nutrition.database.entity.OrderState;
import com.nexing.nutrition.database.repository.CompanyRepository;
import com.nexing.nutrition.database.repository.OrderRepository;
import com.nexing.nutrition.database.repository.ProductRepository;
import com.nexing.nutrition.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;

@Controller
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private OrderService orderService;

    @GetMapping(path = "/api/order")
    public @ResponseBody
    Iterable<Order> getOrders() {
        return orderRepository.findAll();
    }

    @GetMapping(path = "/api/order/{userName}")
    public @ResponseBody
    Iterable<Order> getUserOrders(@PathVariable(value = "userName") String userName) {
        if (userName == null) {
            return orderRepository.findAll();
        }
        return orderRepository.findAllByUserName(userName);
    }

    @PutMapping(path = "/api/order", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Order addOrder(@Valid @RequestBody Order order) {
        if (order.getProductId() != null) {
            productRepository.findById(order.getId()).orElseThrow(() -> new ResourceNotFoundException("Product doesn't exist. Id=" + order.getProductId()));
        }
        if (order.getCompanyId() != null) {
            companyRepository.findById(order.getCompanyId()).orElseThrow(() -> new ResourceNotFoundException("Company doesn't exist. Id=" + order.getCompanyId()));
        } else {
            if (order.getCompanyName() == null) {
                throw new ValidationException("At least one field should be specified: companyName or companyId");
            }
            companyRepository.findFirstByName(order.getCompanyName()).ifPresent(company -> {
                order.setCompanyId(company.getId());
                order.setName(null);
            });
        }
        order.setState(OrderState.NEW);
        return orderRepository.save(order);
    }

    @PutMapping(path = "/api/order/accept/{id}")
    public @ResponseBody
    Order accept(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException, OrderIsAlreadyProcessed {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order doesn't exist. Id=" + id));

        if (order.getState() != OrderState.NEW) {
            throw new OrderIsAlreadyProcessed(id);
        }

        return orderService.acceptOrder(order);
    }

    @PutMapping(path = "/api/order/decline/{id}")
    public @ResponseBody
    Order decline(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException, OrderIsAlreadyProcessed {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order doesn't exist. Id=" + id));
        if (order.getState() != OrderState.NEW) {
            throw new OrderIsAlreadyProcessed(id);
        }

        return orderService.declineOrder(order);
    }
}
