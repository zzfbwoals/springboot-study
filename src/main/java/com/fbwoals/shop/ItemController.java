package com.fbwoals.shop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemController {

    @GetMapping("/list")
    public String list(Model model) { // Thymeleaf 를 사용하기 위한 Model 객체를 전달
        model.addAttribute("name", "홍길동"); // name 에 홍길동을 저장 -> html 에서 ${name} 으로 사용가능
        return "list.html";
    }
}
