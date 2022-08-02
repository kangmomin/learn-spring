package hello.hellospring.controller;

import hello.hellospring.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
    private final MemberService memberService;

//     member service에는 memoryMemberRepo가 상속되지 않기 때문에 memoryMemberRepo를 넣어준다.
//     Autowired기리는 사용이 가능함.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String crewateForm() {
        return "members/createMemberForm";
    }
}
