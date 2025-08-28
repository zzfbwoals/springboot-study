package com.fbwoals.shop.Item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor // Lombok 에서 제공 (덕분에 @Autowired 안써도 됨)
public class ItemController {

    // private final ItemRepository itemRepository; // DB 입출력 함수들이 들어있음
    // Service 사용 시 Repository 관련은 Controller가 아닌 Service에서 사용된다.
    private final ItemService itemService; // DB 입출력 기능

//    @RequiredArgsConstructor 대신 사용 하는 방법 (롬복 안쓸 경우)
//    @Autowired
//    public ItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }

/*
@Autowired는 ItemRepository, ItemService 이름의 타입만 붙여둔 자리에
new ItemRepository(), new ItemService() 알아서 찾아와서 넣으라는 스프링 문법입니다.
 */

    @GetMapping("/list")
    public String list(Model model) { // Thymeleaf 를 사용하기 위한 Model 객체를 전달
//        var result = itemRepository.findAll(); // List<Item> 자료형
//        model.addAttribute("items", result); // name 에 홍길동을 저장 -> html 에서 ${name} 으로 사용가능
        var result = itemService.getItemFindAll(); // itemService 사용
        model.addAttribute("items", result);
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
        // itemRepository.save(item); DB 입출력 기능 함수들은 Service 클래스로 분리해서 저장
        // -> 하나의 클래스에 비슷한 기능의 함수들만 보관하는게 나중에 찾기 쉬움
        // 대신 @Service로 빈 등록하고 Controller 클래스에서 private final로 변수 정의하는 과정 필요
        // new ItemService().saveItem() 하는 방법도 있긴한데 /add 요청마다 object 새로 뽑아야해서 비효율적
        // itemRepository.save(item);
        itemService.saveItem(item); // itemService 사용
        return "redirect:/list"; // ajax 요청일 경우 불가능
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        // var result = itemRepository.findById(id);
        var result = itemService.getItemById(id); // itemService 사용
        // Optional 자료형은 null 일수도 있어서 if 문으로 null 체크 필수 -> 안써도 알아서 잡아주긴 함
        if(result.isPresent()) {
            model.addAttribute("item", result.get());
            return "detail.html";
        }
        else return "redirect:/list";
    }

//    에러 처리 방법
//    @GetMapping("/detail/{id}")
//    ResponseEntity<String> detail() {
//        try {
//            throw new Exception("이런저런에러");
//        } catch(Exception e){
//            return ResponseEntity.status(에러코드).body("에러이유");
//            // ResponseEntity를 쓰는 경우 굳이 @ResponseBody를 붙일 필요 없습니다. 한글 보낼 때 안깨지는 것도 편리함
//        }
//    }
}
