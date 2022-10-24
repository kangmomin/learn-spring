package com.jpabook.jpashop.repository.order;

import com.jpabook.jpashop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// spring data jpa
public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByName(String name);
}
