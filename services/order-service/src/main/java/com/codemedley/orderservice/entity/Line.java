package com.codemedley.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Line {

    @JsonProperty("itemCode")
    private String itemCode;

    @JsonProperty("quantity")
    private int quantity;

    public String getItemCode() {

        return itemCode;
    }

    public void setItemCode(String itemCode) {

        this.itemCode = itemCode;
    }

    public int getQuantity() {

        return quantity;
    }

    public void setQuantity(int quantity) {

        this.quantity = quantity;
    }
}
