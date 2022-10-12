package com.jpabook.jpashop.dto;

import lombok.Data;

@Data
public class OrderItemQueryDto {

    private Long orderId;
    private String itemName;
    private int price;
    private int count;

    public OrderItemQueryDto(Long orderId, String itemName, int price, int count) {
        this.orderId = orderId;
        this.itemName = itemName;
        this.price = price;
        this.count = count;
    }

}
