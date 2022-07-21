package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepo {
    Member save(Member member);
    Optional<Member> findById(Long id); // optional == if val null return null
    Optional<Member> findByName(Long name);
    List<Member> findAll();
}
