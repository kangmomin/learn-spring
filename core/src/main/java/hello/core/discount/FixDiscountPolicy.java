package hello.core.discount;

import hello.core.member.Gradle;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("fixDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy {
    private final int discountFixAmount = 1000; // 1000원의 할인가

    @Override
    public int discount(Member member, int price) {
        if (member.getGradle() == Gradle.VIP) {
            return discountFixAmount;
        }
        return 0;
    }
}
