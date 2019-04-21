package com.nexing.nutrition.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class OrderIsAlreadyProcessed extends RuntimeException {

    public OrderIsAlreadyProcessed(Integer id) {
        super("Order is already processed. Id = " + id);
    }
}
