package com.codemedley.orderservice.service;


import com.codemedley.orderservice.entity.Order;
import com.codemedley.orderservice.exception.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderProcessingService {

    private final Map<String, Order> orders = new HashMap<>();

    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        System.out.println("Received Order For " + order.getItems().size() + " Items");
        order.getItems().forEach((lineItem) ->
                System.out.println("Item: " + lineItem.getItemCode() + " Quantity: " + lineItem.getQuantity())
        );

        String orderId = UUID.randomUUID().toString();
        order.setOrderId(orderId);
        orders.put(orderId, order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable String id) throws OrderNotFoundException {
        if (orders.containsKey(id)) {
            return new ResponseEntity<>(orders.get(id), HttpStatus.OK);
        } else {
            throw new OrderNotFoundException();
        }
    }
}