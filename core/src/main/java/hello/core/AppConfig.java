package hello.core;

import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepo;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {

        return new MemberServiceImpl(memberRepo());
    }

    @Bean
    public MemoryMemberRepo memberRepo() {
        return new MemoryMemberRepo();
    }

    @Bean
    public OrderService orderService() {

        return new OrderServiceImpl(memberRepo(), discountPolicy());
    }

    @Bean
    public RateDiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
