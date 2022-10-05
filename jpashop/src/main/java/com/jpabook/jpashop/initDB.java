package com.jpabook.jpashop;

import com.jpabook.jpashop.domain.*;
import com.jpabook.jpashop.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class initDB {

    private InitServer initServer;

    @PostConstruct
    public void init() {
        initServer.dbInit();
        initServer.dbInit2();
    }

    @Component
    @RequiredArgsConstructor
    @Transactional
    static class InitServer {

        private final EntityManager em;
        public void dbInit() {
            Member member1 = createMember("member a");

            em.persist(member1);

            Book book1 = createBook();

            Book book2 = createBook();

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

            Delivery delivery = new Delivery();
            delivery.setAddress(member1.getAddress());

            Order order = Order.createOrder(member1, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        private static Book createBook() {
            Book book1 = new Book();
            book1.setName("testBook");
            book1.setPrice(1234);
            book1.setStockQuantity(100);
            return book1;
        }

        public void dbInit2() {
            Member member = createMember("member b");

            em.persist(member);

            Book book1 = createBook();

            createBook();

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book1, 20000, 2);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());

            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        private static Member createMember(String member_b) {
            Member member = new Member();
            member.setName(member_b);
            member.setAddress(new Address("서울", "테스트", "1231"));
            return member;
        }
    }
}
