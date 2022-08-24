package hello.core.discount;

import hello.core.member.Gradle;
import hello.core.member.Member;

public class RateDiscountPolicy implements DiscountPolicy {

    private final int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGradle() == Gradle.VIP) {
            return price * discountPercent / 100;
        }

        return 0;
    }
}
