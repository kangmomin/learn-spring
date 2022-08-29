package hello.core.discount;

import hello.core.member.Gradle;
import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RateDiscountPolicyTest {

    DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10%할인이 적용되어야 한다.")
    void vip_o() {
        //given
        Member member = new Member(1L, "memberVIP", Gradle.VIP);

        //when
        int discount = discountPolicy.discount(member, 10000);

        //then
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP는 10%할인이 적용되어야 한다. error가 나야함")
    void vip_o_err() {
        //given
        Member member = new Member(2L, "memberVIP", Gradle.VIP);

        //when
        int discount = discountPolicy.discount(member, 10000);

        //then
        assertThat(discount).isNotEqualTo(0);
    }

    @Test
    @DisplayName("BASIC는 10%할인이 적용되지 않아야 한다.")
    void vip_x() {
        //given
        Member member = new Member(3L, "memberVIP", Gradle.BASIC);

        //when
        int discount = discountPolicy.discount(member, 10000);

        //then
        assertThat(discount).isEqualTo(0);
    }

    @Test
    @DisplayName("BASIC는 10%할인이 적용되지 않아야 한다. error가 나야함.")
    void vip_x_err() {
        //given
        Member member = new Member(4L, "memberVIP", Gradle.BASIC);

        //when
        int discount = discountPolicy.discount(member, 10000);

        //then
        assertThat(discount).isNotEqualTo(1000);
    }
}