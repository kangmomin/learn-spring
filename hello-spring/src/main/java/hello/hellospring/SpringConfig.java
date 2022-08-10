package hello.hellospring;

import hello.hellospring.Service.MemberService;
import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//추후 MemberRepo의 생성자 코드만 살짝 바꾸면 다른 코드 수정 없이 DB를 변경하는 등의 작업이 가능함.
@Configuration
public class SpringConfig {

//    @Autowired DataSource dataSource;
//    EntityManager em;
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }
    private final MemberRepo memberRepo;

    @Autowired
    public SpringConfig(MemberRepo memberRepo) {
        this.memberRepo = memberRepo;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepo);
    }

    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }

//    @Bean
//    public MemberRepo memberRepo() {
//        return new JpaMemberRepo(em);
//    }
}
