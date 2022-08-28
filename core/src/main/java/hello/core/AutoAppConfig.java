package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.springframework.context.annotation.ComponentScan.*;

@Configuration
@ComponentScan (
        // default는 해당 config를 선언한 패키지를 시작으로 하위로 내려감.
        basePackages = "hello.core.member", // 컴포넌트 스캔의 시작위치를 설정함.
        excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
