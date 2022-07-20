package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello") // Xmapping(N) == X메소드로 받음. post, put, patch, get, delete, etc...
    // N이라는 패스를 할당받음.
    public String hello(Model m) { // router name
        m.addAttribute("data", "hello!");
        return "hello";
        // hello 라는 string을 리턴하는 라우터가 있으면 thymeleaf템플릿 엔진에선
        // 해당 string값과 동일한 이름을 가진 temp에 값을 적용시킨다
    }

    @GetMapping("hello-mvc")
//    required값을 통해 필수값의 유무를 설정 가능
    public String helloMvc(@RequestParam(name = "name", required = false) String name, Model m) {
        m.addAttribute("name", name);
        return "hello-template";
    }

    static class Hello {
        private String name;
        public String getName() {
            return name;
        }

        Hello(String name) {
            this.name = name;
        }
    }

    @GetMapping("hello-string")
    @ResponseBody
    public Hello helloString(@RequestParam("name") String name) {
        Hello hello = new Hello(name);
        return hello;
    }
}
