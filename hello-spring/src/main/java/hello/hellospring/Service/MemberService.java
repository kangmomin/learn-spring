package hello.hellospring.Service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service
// 데이터를 저장 변경시엔 트랜잭션이 필요함.
@Transactional
public class MemberService {
    private final MemberRepo memberRepo;

//    @Autowired private MemberRepo memberRepo;
//    field주입 선언 당시를 제외하곤 값의 변경이 불가하기에 비추.

//    memberRepo를 초기화하기 위한 생성자.
//    Autowired는 spring bean에 등록을 자동으로 해주는 어노테이션
//    Spring bean은 객체인데 Spring IoC에서 관리하는 객체임.
//    @Container가 포함된 클래스는 자동으로 Spring bean에 등록됨.
//    아래와 같이 생성자에 클래스명을 붙이면 해당 이름의 bean에 있는 객체를 반환해줌.

//    생성자 주입
//    생성 시점에만 변경후 변경을 하지 않기 때문에 안전하다.
//    변수에 final이 붙을 수 있는 이유는 생성자에서 성정하기 때문인 것 같다.
    @Autowired
    public MemberService(MemberRepo memberRepo) {
        this.memberRepo = memberRepo;
    }

//    setter 주입
//    퍼블릭이라 아무 개발자가 접근이 가능해서 요즘은 거의 사용치 않음.
//    @Autowired
//    public void setMemberRepo(MemberRepo memberRepo) {
//        this.memberRepo = memberRepo;
//    }

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
