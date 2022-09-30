package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.Address;
import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.domain.Order;
import com.jpabook.jpashop.domain.OrderStatus;
import com.jpabook.jpashop.domain.item.Book;
import com.jpabook.jpashop.exeption.NotEnoughStockExeption;
import com.jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class OrderServiceTest {
    @Autowired EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    @Test
    public void 상품주문() {
        Member member = createMember("a");
        em.persist(member);

        Book book = createBook("jpa", 10000, 10);
        em.persist(book); // book은 cascade가 적용되지 않았음

        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        Order getOrder = orderRepository.findOne(orderId);

        assertEquals(OrderStatus.ORDER, getOrder.getStatus());
        assertEquals(1, getOrder.getOrderItems().size());
        assertEquals(10000 * orderCount, getOrder.getTotalPrice());
        assertEquals(8, book.getStockQuantity());
    }

    private static Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        return book;
    }

    private static Member createMember(String name) {
        Member member = new Member();
        member.setName(name);
        member.setAddress(new Address("서울", "강가", "123-123"));
        return member;
    }

    @Test
    public void 상품주문_재고수량초과() {
        Member member = createMember("a");
        em.persist(member);

        Book book = createBook("jpa", 10000, 10);
        em.persist(book);

        int orderCount = 11;

        assertThrows(NotEnoughStockExeption.class, () -> {
            orderService.order(member.getId(), book.getId(), orderCount);
            fail("재고 수량 부족 예외가 발생해야 한다.");
        });
    }

    @Test
    public void 주문취소() {
        Member member = createMember("a");
        Book book = createBook("시골 JPA", 10000, 10);

        em.persist(member);
        em.persist(book);

        Long orderId = orderService.order(member.getId(), book.getId(), 2);

        orderService.cancelOrder(orderId);

        Order getOrder = orderRepository.findOne(orderId);

        assertEquals(OrderStatus.CANCEL, getOrder.getStatus());
        assertEquals(10, book.getStockQuantity());

    }

}