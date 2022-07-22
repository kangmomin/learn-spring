package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepoTest {
    MemberRepo repo = new MemoryMemberRepo();

    // 각 테스트가 끝날 때 마다 repository를 clear해주기.
    @AfterEach
    public void afterEach() {
        repo.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");
        repo.save(member);

        Member result = repo.findById(member.getId()).get();
        Assertions.assertEquals(result, member);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repo.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repo.save(member2);

        Member result =  repo.findByName("spring1").get();
        Assertions.assertEquals(member1, result);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repo.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repo.save(member2);

        List<Member> result = repo.findAll();

        Assertions.assertEquals(result.size(), 2);
    }
}
