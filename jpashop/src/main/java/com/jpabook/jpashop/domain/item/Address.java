package com.jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Embeddable
@Getter @Setter
public class Address {

    private String city;
    private String street;
    private String zipcode;
}
