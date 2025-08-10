package com.codemedley.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Order {

    @JsonProperty("orderId")
    private String orderId;

    @JsonProperty("items")
    private List<Line> items;

    @JsonProperty("shippingAddress")
    private String shippingAddress;

    public Order(String orderId, List<Line> items, String shippingAddress) {
        this.orderId = orderId;
        this.items = items;
        this.shippingAddress = shippingAddress;
    }

    public Order() {
        // Default constructor for deserialization
    }

    public String getOrderId() {

        return orderId;
    }

    public void setOrderId(String orderId) {

        this.orderId = orderId;
    }

    public List<Line> getItems() {

        return items;
    }

    public void setItems(List<Line> items) {

        this.items = items;
    }

    public String getShippingAddress() {

        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {

        this.shippingAddress = shippingAddress;
    }
}
