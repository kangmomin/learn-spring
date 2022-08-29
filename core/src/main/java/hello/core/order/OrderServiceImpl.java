package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor // final private 변수를 생성자로 만들어줌.(Lombok)
public class OrderServiceImpl implements OrderService {

    private final MemberRepo memberRepo;
    private final DiscountPolicy discountPolicy;
    
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepo.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
    public MemberRepo getMemberRepo() {
        return memberRepo;
    }
}
