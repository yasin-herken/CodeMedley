package com.codemedley.orderservice.service;


import com.codemedley.orderservice.entity.Order;
import com.codemedley.orderservice.exception.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderProcessingService {

    private final Map<String, Order> orders = new HashMap<>();

    @Value("${inventory.service}")
    private String inventoryServiceUrl;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody Order order) {

        if (order != null) {
            RestTemplate restTemplate = new RestTemplate();
            URI uri = URI.create(inventoryServiceUrl);
            restTemplate.put(uri, order.getItems());

            order.setOrderId(UUID.randomUUID().toString());
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(order.getOrderId()).toUri();

            return ResponseEntity.created(location).build();
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
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