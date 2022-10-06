package com.jpabook.jpashop.repository.order.simpleQuery;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {

    private final EntityManager em;


    public List<OrderSimpleQueryDto> findOrderDtos() {
        List<OrderSimpleQueryDto> result = em.createQuery(
                        "select new com.jpabook.jpashop.repository.order.simpleQuery.OrderSimpleQueryDto(o.id, m.name, o.orderDate, o.status, d.address) " +
                                "from Order o " +
                                "join o.member m " +
                                "join o.delivery d ", OrderSimpleQueryDto.class)
                .getResultList();

        return result;
    }
}
