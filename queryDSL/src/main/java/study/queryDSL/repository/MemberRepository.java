package study.querydsl.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.querydsl.entity.Member;
import study.querydsl.entity.QMember;

import java.util.List;

import static study.querydsl.entity.QMember.*;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final JPAQueryFactory query;

    public List<Member> findAll() {
        return query
                .selectFrom(member)
                .fetch();
    }

    public List<Member> findByUsername(String username) {
        return query
                .selectFrom(member)
                .where(member.username.eq(username))
                .fetch();
    }
}
