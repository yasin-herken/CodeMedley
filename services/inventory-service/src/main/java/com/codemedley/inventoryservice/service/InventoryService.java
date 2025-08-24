package com.codemedley.inventoryservice.service;

import com.codemedley.inventoryservice.entity.Item;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/inventory")
public class InventoryService {

    @PutMapping
    public ResponseEntity<?> updateItems(@RequestBody Item[] items) {

        if (items == null || items.length == 0) {
            return ResponseEntity.badRequest().build();
        }
        for (Item item : items) {
            if (item != null) {
                System.out.println(item.getCode());
            }
        }
        return ResponseEntity.noContent().build();
    }
}