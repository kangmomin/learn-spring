package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepo extends JpaRepository<Member, Long>, MemberRepo {

    @Override
    Optional<Member> findByName(String name);
}
