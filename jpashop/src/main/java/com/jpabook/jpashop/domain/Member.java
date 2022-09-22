package com.jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();


}
