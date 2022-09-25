package com.jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.*;

@Embeddable
// 값타입은 세터를 만들면 안됨
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    // 임베디드 타입은 protected나 public으로 해둬야함.
    protected Address() {}
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
