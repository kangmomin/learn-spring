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

    private final InitServer initServer;

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
            Member member = createMember("member a");

            em.persist(member);

            Book book1 = createBook("test1", 10000, 1000);
            Book book2 = createBook("test2", 10000, 100);
            em.persist(book1);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            em.persist(delivery);

            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        private static Book createBook(String name, int price, int stockQuantity) {
            Book book1 = new Book();
            book1.setName(name);
            book1.setPrice(price);
            book1.setStockQuantity(stockQuantity);
            return book1;
        }

        public void dbInit2() {
            Member member = createMember("member b");

            em.persist(member);

            Book book1 = createBook("test3", 40000, 1000);
            Book book2 = createBook("test4", 25000, 100);
            em.persist(book1);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 40000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 50000, 2);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            em.persist(delivery);

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
