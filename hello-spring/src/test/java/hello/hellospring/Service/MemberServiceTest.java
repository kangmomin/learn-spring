package hello.hellospring.Service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepo memoryMemberRepo = new MemoryMemberRepo();

//    매 동작 전 memberService의 repo를 쵝화 해줌.
    @BeforeEach
    void beforeEach() {
        MemoryMemberRepo memberRepo = new MemoryMemberRepo();
        memberService = new MemberService(memberRepo);
    }

    @AfterEach
    void afterEach() {
        memoryMemberRepo.clearStore();
    }

    @Test
    void join_user() {
        // given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }

    @Test
    void join_duplicate_error_test() {
        // given
        Member m1 = new Member();
        m1.setName("spring");

        Member m2 = new Member();
        m2.setName("spring");

        // when
        memberService.join(m1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(m2));
        // error가 없을땐 테스트 실패로 인한 빨간 밑줄이 assertThrows에 뜬다.

        assertThat(e.getMessage()).isEqualTo("data is already joined.");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}