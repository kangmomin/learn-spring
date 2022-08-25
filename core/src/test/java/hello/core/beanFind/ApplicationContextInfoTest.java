package hello.core.beanFind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력")
    void findAllBean() {
        String[] beamDefinitionNames = ac.getBeanDefinitionNames();
        for (String beamDefinitionName : beamDefinitionNames) {
            Object bean = ac.getBean(beamDefinitionName);
            System.out.println("name = " + beamDefinitionName + " object = " + bean);
        }
    }
    @Test
    @DisplayName("app 빈 출력")
    void findAppicationBean() {
        String[] beamDefinitionNames = ac.getBeanDefinitionNames();
        for (String beamDefinitionName : beamDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beamDefinitionName);
//            ROLE_APPLICATION == 직접 등록한 빈
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beamDefinitionName);
                System.out.println("name = " + beamDefinitionName + " object = " + bean);
            }
        }
    }
}
