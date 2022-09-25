package com.jpabook.jpashop.domain;

import com.jpabook.jpashop.domain.item.Address;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    private Address address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<Order> orders = new ArrayList<>();


}
