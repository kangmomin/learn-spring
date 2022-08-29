package hello.core.beanFind;

import hello.core.member.MemberRepo;
import hello.core.member.MemoryMemberRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextSameBeanFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있을 경우 중복 오류 발생")
    void findBeanByTypeDuplicate() {
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(MemberRepo.class));
    }
    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있을 경우 빈의 이름을 지정하면 된다")
    void findBeanByName() {
        ac.getBean("memberRepo1", MemberRepo.class);
        assertThat(NoUniqueBeanDefinitionException.class).isNotInstanceOf(MemberRepo.class);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType() {
        Map<String, MemberRepo> beansOfType = ac.getBeansOfType(MemberRepo.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
        System.out.println("beans of type = " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Configuration
    static class SameBeanConfig {

        @Bean
        public MemberRepo memberRepo1() {
            return new MemoryMemberRepo();
        }
        @Bean
        public MemberRepo memberRepo2() {
            return new MemoryMemberRepo();
        }
    }
}
