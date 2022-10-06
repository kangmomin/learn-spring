package com.jpabook.jpashop.api;

import com.jpabook.jpashop.domain.Address;
import com.jpabook.jpashop.domain.Order;
import com.jpabook.jpashop.domain.OrderStatus;
import com.jpabook.jpashop.repository.OrderRepository;
import com.jpabook.jpashop.repository.OrderSearch;
import com.jpabook.jpashop.repository.order.simpleQuery.OrderSimpleQueryDto;
import com.jpabook.jpashop.repository.order.simpleQuery.OrderSimpleQueryRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * order
 * order -> member
 * order -> delivery
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
    private final OrderSimpleQueryRepository orderSimpleQueryRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByCriteria(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName(); // Lazy 강제 초기화
            order.getDelivery().getAddress(); // Lazy 강제 초기화
        }
        return all;
    }

    @GetMapping("/api/v2/simple-orders")
    public Result ordersV2() {
        //  N + 1 문제
        // N다수의 값을 기준으로 할 때 1(최초 조회) + N(회원) + N(배송) 의 문제가 일어남
        List<Order> orders = orderRepository.findAllByCriteria(new OrderSearch());
        List<SimpleOrderDto> collect = orders.stream()
                .map(SimpleOrderDto::new) // o -> new simpleOrderDto(o) / 생성자를 바로 반환함.
                .collect(Collectors.toList());
        return new Result(collect);
    }

    // 성능이 v4보다는 낮지만 재사용이 요잉함
    @GetMapping("/api/v3/simple-orders")
    public Result ordersV3() {
        List<Order> findOrder = orderRepository.findAllWithMemberDelivery();
        List<SimpleOrderDto> result = findOrder.stream()
                .map(SimpleOrderDto::new)
                .collect(Collectors.toList()); // 지연로딩을 없애고 쿼리 양을 줄임

        return new Result(result);
    }

    // 성능은 좋으나 재사용이 힘듦
    @GetMapping("/api/v4/simple-orders")
    public Result ordersV4() {
        List<OrderSimpleQueryDto> result = orderSimpleQueryRepository.findOrderDtos();

        return new Result(result);
    }

    @Data
    static class SimpleOrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order) {
            this.orderId = order.getId();
            this.name = order.getMember().getName();
            this.orderDate = order.getOrderDate();
            this.orderStatus = order.getStatus();
            this.address = order.getDelivery().getAddress();
        }
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }
}
