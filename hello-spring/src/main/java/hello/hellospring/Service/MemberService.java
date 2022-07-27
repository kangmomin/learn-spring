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
//    Autowired는 spring bean에 등록을 자동으로 해주는 어노테이션
//    Spring bean은 객체인데 Spring IoC에서 관리하는 객체임.
//    @Container가 포함된 클래스는 자동으로 Spring bean에 등록됨.
//    아래와 같이 생성자에 클래스명을 붙이면 해당 이름의 bean에 있는 객체를 반환해줌.
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
