package hello.core.lifeCycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
public class BeanLifeCycleTest {
    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }
    @Configuration
    static class LifeCycleConfig {
//        @Bean(initMethod = "init", destroyMethod = "close") // 초기화 메소드, 종료메소드.
        // 스프링 빈이 코드애 의존하지 않으며 설정정보이기에 외부 라이브러리에도 사용 가능.
        // destory는 기본값이 inferred(추론)으로 close나 shutdown과 같은 값을 추론해서 적용한다.

        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}