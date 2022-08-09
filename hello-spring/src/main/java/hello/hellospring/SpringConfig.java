package hello.hellospring;

import hello.hellospring.Service.MemberService;
import hello.hellospring.repository.MemberRepo;
import hello.hellospring.repository.jdbcMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//추후 MemberRepo의 생성자 코드만 살짝 바꾸면 다른 코드 수정 없이 DB를 변경하는 등의 작업이 가능함.
@Configuration
public class SpringConfig {

    @Autowired DataSource dataSource;

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepo());
    }

    @Bean
    public MemberRepo memberRepo() {
        return new jdbcMemberRepository(dataSource);
    }
}
