package hello.hellospring;

import hello.hellospring.Service.MemberService;
import hello.hellospring.repository.MemberRepo;
import hello.hellospring.repository.MemoryMemberRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepo());
    }

    @Bean
    public MemberRepo memberRepo() {
        return new MemoryMemberRepo();
    }
}
