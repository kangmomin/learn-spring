package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.repository.MemberRepositoryOld;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired
    MemberRepositoryOld memberRepository;
    @Autowired EntityManager em;

    @Test
    public void 회원가입() throws Exception {
        Member m = new Member();
        m.setName("hello");

        Long joinedMember = memberService.join(m);

        assertEquals(m, memberRepository.findOne(joinedMember));
    }

    @Test
    public void 중복_회원_예외() throws Exception {

        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        try {
            memberService.join(member1);
            memberService.join(member2);
            em.flush();
        } catch (IllegalStateException e) {
            return;
        }

        fail("예외가 발생해야 한다."); // 해당 부분이 작동되면 안단다고 선언함
    }
}