package com.fbwoals.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor // Lombok 에서 제공 (덕분에 @Autowired 안써도 됨)
public class ItemController {

    private final ItemRepository itemRepository; // DB 입출력 함수들이 들어있음

//    @RequiredArgsConstructor 대신 사용 하는 방법 (롬복 안쓸 경우)
//    @Autowired
//    public ItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }

    @GetMapping("/list")
    public String list(Model model) { // Thymeleaf 를 사용하기 위한 Model 객체를 전달
        var result = itemRepository.findAll(); // List<Item> 자료형
        System.out.println(result.get(0));
        model.addAttribute("name", "홍길동"); // name 에 홍길동을 저장 -> html 에서 ${name} 으로 사용가능
        return "list.html";
    }
}
