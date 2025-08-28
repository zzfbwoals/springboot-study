package com.fbwoals.shop.ETC;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

// REST API
// 1. Unifrom Interface: 비슷한 기능을 하는 API들은 URL과 method가 유사성, 일관성이 있어야 함.
// 2. Client-server 역할 구분: 유저에게 서버역할을 맡기거나 DB를 직접 입출력하게 시키면 안좋음.
// 3. Stateless: 요청들은 서로 의존성이 있으면 안되고 각각 독립적으로 처리.
// 4. Cacheable: 서버가 보내는 자료들은 캐싱이 가능해야 함. 자주 받는 자료들은 브라우저에서 하드에 저장해놓고 서버에 요청을 날리는게 아니라 하드에서 뽑아쓰는 것이 캐싱.
// 5. Layered System: 서버 기능을 만들 때 레이어를 걸쳐서 코드가 실행되도록 만들어도 됨.
// 6. Code on demand: 서버는 실행가능한 코드를 보낼 수 있음.

// 예쁜 URL 만들기
// 1. 동사보단 명사 위주로 구성
// 2. 띄어쓰기는 언더바_대신 대시-기호-사용
// 3. 파일 확장자 쓰지 말기 (.html)
// 4. 하위 항목을 표현하고 싶으면 / 기호 사용
// ex) facebook.com/bbc/photos -> 페북에서 bbc 계정의 사진첩 보여줄거 같음

// 컨트롤러는 API를 담고 있는 클래스
@Controller
public class BasicController {

    // GET: 서버에게 데이터를 달라고 할 때
    // POST: 서버에게 데이터를 보내고 싶을 때
    // PUT: 서버에게 데이터를 업데이트(수정) 할 때
    // DELETE: 서버에게 데이터를 삭제 할 때
    // 기본적으로 url 입력하면 GET 요청이 들어오고 POST, PUT, DELETE 요청은 <form> 태그나 AJAX를 이용해서 전송해야 함
    @GetMapping("/test") // url에 해당하는 요청이 들어오면
    @ResponseBody // return 값이 .html 이면 안써도 됨
    public String test() {

        var object = new Person("류재민", 24);
        System.out.println(object.toString());
        object.addAge();
        System.out.println(object.toString());
        object.setAge(-10);
        System.out.println(object.toString());
        return "테스트임";
    }

    @PostMapping("/test1")
    String test(@RequestBody Map<String, Object> body) {
        System.out.println(body.get("name"));
        return "redirect:/list";
    }

    @GetMapping("/test2")
    // html 파일을 보내주고 싶으면 @ResponseBody 빼고 "html 파일명"을 return 해주면 됨
    public String test2() {

        return "test.html";
    }

    @GetMapping("/study")
    public String study() {

        return "study.html";
    }
}
