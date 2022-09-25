package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
//@RequiredArgsConstructor // final이 붙은 변수들로만 생성자를 만들어줌.(추천)
public class MemberService {

    private final MemberRepository memberRepository;

    // 생성자 인잭션을 써야 테스트시 인자 값을 수정하기 수월하고 런타임중 변경이 일어나는걸 방지할 수 있음.
    // 생성자는 자동으로 autowired를 적용함.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 가입
    @Transactional
    public Long  join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findByName = memberRepository.findByName(member.getName());
        if (!findByName.isEmpty()) {
            throw new IllegalStateException("member is already signed up");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long id) {
        return memberRepository.findOne(id);
    }
}
