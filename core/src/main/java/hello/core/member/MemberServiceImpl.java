package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {
    private final MemberRepo memberRepo;

    @Autowired // 해당 타입을 리턴해주는 클래스를 찾아 인자로 넣어준다.(자동 등록 = component어노테이션)
    public MemberServiceImpl(MemberRepo memberRepo) {
        this.memberRepo = memberRepo;
    }

    @Override
    public void join(Member member) {
        memberRepo.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepo.findById(memberId);
    }

    // for test
    public MemberRepo getMemberRepo() {
        return memberRepo;
    }
}
