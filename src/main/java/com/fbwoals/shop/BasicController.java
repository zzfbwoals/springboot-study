package com.fbwoals.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BasicController {

    @GetMapping("/test") // url에 해당하는 요청이 들어오면
    @ResponseBody // return 값이 .html 이면 안써도 됨
    public String test() {

        return "테스트임";
    }

    @GetMapping("/test2")
    // html 파일을 보내주고 싶으면 @ResponseBody 빼고 "html 파일명"을 return 해주면 됨
    public String test2() {

        return "test.html";
    }
}
