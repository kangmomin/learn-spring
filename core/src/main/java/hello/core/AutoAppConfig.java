package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.springframework.context.annotation.ComponentScan.*;

@Configuration
@ComponentScan (
        // default는 해당 config를 선언한 패키지를 시작으로 하위로 내려감.
        basePackages = "hello.core", // 컴포넌트 스캔의 시작위치를 설정함.
        excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
//    수동 빈 등록 vs 자동 빈 등록
//    수동 빈이 우선권을 가져가 에러가 나지 않음.
//    하지만 최근 버전의 스프링 부트는 에러가 나도록 바뀜.
//    @Bean(name = "memoryMemberRepo")
//    public MemberRepo memberRepo() {
//        return new MemoryMemberRepo();
//    }
}
