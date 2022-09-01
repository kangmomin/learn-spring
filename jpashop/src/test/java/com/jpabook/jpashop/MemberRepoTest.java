package com.jpabook.jpashop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MemberRepoTest {
    @Autowired MemberRepo memberRepo;

    @Test
    @Transactional
    public void testMember() throws Exception {
        Member member = new Member();
        member.setUserName("memberA");

        Long saveId = memberRepo.save(member);
        Member findMember = memberRepo.find(saveId);

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUserName()).isEqualTo(member.getUserName());
        System.out.println("findMember == member: " + (findMember == member));
    }
}