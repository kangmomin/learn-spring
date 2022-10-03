package com.jpabook.jpashop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateItemDTO {

    private int price;
    private String name;
    private int stockQuantity;

    public UpdateItemDTO(int price, String name, int stockQuantity) {
        this.price = price;
        this.name = name;
        this.stockQuantity = stockQuantity;
    }
}
