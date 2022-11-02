package study.querydsl;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.entity.Member;
import study.querydsl.entity.QMember;
import study.querydsl.entity.Team;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static study.querydsl.entity.QMember.*;

@SpringBootTest
@Transactional
public class QuerydslBasicTest {

    @Autowired
    EntityManager em;
    JPQLQueryFactory query = new JPAQueryFactory(em);

    @BeforeEach
    public void before() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");

        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
    }

    @Test
    public void startJpql() {
        Member findByJpql = em.createQuery(
                        "select m from Member m where m.username = :username", Member.class)
                .setParameter("username", "member1")
                .getSingleResult();

        assertThat(findByJpql.getUsername()).isEqualTo("member1");
    }

    @Test
    public void startDsl() {
        QMember m = new QMember("m"); // as m으로 query를 짜줌. 같은 테이블을 조인할 때 사용

        Member result = query.select(m)
                .from(m)
                .where(m.username.eq("member1"))
                .fetchOne();

        assertThat(result.getUsername()).isEqualTo("member1");
    }

    @Test
    public void search() {
        Member member1 = query.selectFrom(member)
                .where(
                        member.username.eq("member1"),
                        member.age.eq(10)
                )
                .fetchOne();

        assertThat(member1.getUsername()).isEqualTo("member1");
        assertThat(member1.getAge()).isEqualTo(10);
    }

    @Test
    public void resultFetch() {
        List<Member> fetch = query.selectFrom(member)
                .fetch();

        Member member1 = query.selectFrom(member)
                .fetchOne();

        Member member2 = query.selectFrom(member)
                .fetchFirst();

        // paging 관련
        // 자동 성능 최적화를 위해 복잡해 졌을 땐 값이 다르게 나옴 => 그땐 따로 쿼리 2방 날림
        QueryResults<Member> memberQueryResults = query.selectFrom(member)
                .fetchResults();

        memberQueryResults.getTotal();
        List<Member> results = memberQueryResults.getResults();

        // count 쿼리
        long l = query.selectFrom(member)
                .fetchCount();
    }
}
