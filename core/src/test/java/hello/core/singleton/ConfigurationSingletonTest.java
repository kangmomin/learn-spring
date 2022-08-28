package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepo;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        MemberServiceImpl orderService = ac.getBean("orderService", MemberServiceImpl.class);
        MemberRepo memberRepo = ac.getBean("memberRepo", MemberRepo.class);

        MemberRepo memberRepo1 = memberService.getMemberRepo();
        MemberRepo memberRepo2 = orderService.getMemberRepo();

        System.out.println("memberService -> memberRepo = " + memberRepo1);
        System.out.println("orderService -> memberRepo = " + memberRepo2);
        System.out.println("MemoryMemberRepo = " + memberRepo);
        Assertions.assertThat(memberService.getMemberRepo()).isSameAs(memberRepo);
        Assertions.assertThat(orderService.getMemberRepo()).isSameAs(memberRepo);

    }
}
