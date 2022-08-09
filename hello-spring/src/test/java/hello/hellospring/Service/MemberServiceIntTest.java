package hello.hellospring.Service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


// 그냥 테스트시 스프링을 띄워서 테스트하진 않으나
// 아래의 어노테이션을 붙여서 테스트시 스프링을 띄워 테스트를 진행한다다
@SpringBootTest
// test이후 데이터를 초기화 해줌.
// DB데이터를 커밋하지 않고 롤백 해줌.
@Transactional
class MemberServiceIntTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepo memoryMemberRepo;

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
}