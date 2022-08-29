package hello.core.discount;

import hello.core.member.Gradle;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Qualifier("mainDiscountPolicy")
@Primary // 해당 어노테이션이 붙은 것을 가장 먼저 넣어준다.
// 만약 Qualifier와 primary를 둘 다 사용했을땐 더욱 자세한 Qulifier가 우선권을 획득한다.
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
