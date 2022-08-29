package hello.core.discount;

import hello.core.member.Gradle;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("mainDiscountPolicy")
public class RateDiscountPolicy implements DiscountPolicy {

    @Override
    public int discount(Member member, int price) {
        if (member.getGradle() == Gradle.VIP) {
            int discountPercent = 10;
            return price * discountPercent / 100;
        }

        return 0;
    }
}
