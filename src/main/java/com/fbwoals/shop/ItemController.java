package com.fbwoals.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
        model.addAttribute("items", result); // name 에 홍길동을 저장 -> html 에서 ${name} 으로 사용가능
        return "list.html";
    }

    @GetMapping("/write")
    public String write() {
        return "write.html";
    }

//    @PostMapping("/add")
//    public String add(String title, Integer price) { // @RequestParam 생략
//        Item item = new Item();
//        item.setTitle(title); item.setPrice(price);
//        itemRepository.save(item);
//        return "redirect:/list"; // ajax 요청일 경우 불가능
//    }
    // 위의 코드 간단 버전
    @PostMapping("/add")
    public String add(@ModelAttribute Item item) {
        itemRepository.save(item);
        return "redirect:/list"; // ajax 요청일 경우 불가능
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        var result = itemRepository.findById(id);
        // Optional 자료형은 null 일수도 있어서 if 문으로 null 체크 필수 -> 안써도 알아서 잡아주긴 함
        if(result.isPresent()) {
            model.addAttribute("item", result.get());
            return "detail.html";
        }
        else return "redirect:/list";
    }
}
