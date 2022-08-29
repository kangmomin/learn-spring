package hello.core.Order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.*;
import hello.core.order.Order;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    @Test
    void createOrder() {
        MemoryMemberRepo memberRepo = new MemoryMemberRepo();
        memberRepo.save(new Member(1L, "name", Gradle.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepo, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
