package hello.core.lifeCycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {
    private String url;

    public NetworkClient() {
        System.out.println("generator, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String msg) {
        System.out.println("call: " + url + " msg = " + msg);
    }

    public void disconnect() {
        System.out.println("close + " + url);
    }

    // 초기화 이후 코드가 실행되게 할 수 있다. // InitializingBean
    // 하지만 스프링 코드라 변경이 힘들다

    // 이거 쓰자
    // 스프링이 아닌 자바단에서 지원하는 자바표준 기술임.
    // 하지만 외부 라이브러리엔 적용이 힘드니 그때만 Bean설정을 쓰도록 한다.
    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 메시지");
    }

    // 코드가 끝날때 먼저 실행된다. // DisposableBean

    @PreDestroy
    public void close() {
        disconnect();
    }
}
