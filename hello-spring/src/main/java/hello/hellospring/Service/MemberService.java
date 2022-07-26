package hello.hellospring.Service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepo memberRepo;

//    memberRepo를 초기화하기 위한 생성자.
    @Autowired
    public MemberService(MemberRepo memberRepo) {
        this.memberRepo = memberRepo;
    }

//    sign up
    public Long join(Member member) {
        isVaildDuplicateMember(member);
        memberRepo.save(member);
        return member.getId();
    }

    private void isVaildDuplicateMember(Member member) {
        memberRepo.findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("data is already joined.");
            });
    }

//    전체 회원 조회
    public List<Member> findMembers() {
        return memberRepo.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepo.findById(memberId);
    }
}
