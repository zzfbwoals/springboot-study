package com.fbwoals.shop.Member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLOutput;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public String signup(Authentication auth) {

        // null 체크를 안해주면 로그인 안하고 signup 페이지로 갈 시 에러 발생
        if(auth != null && auth.isAuthenticated()) return "redirect:/list";
        return "signup.html";
    }

    @PostMapping("/signup")
    public String addMember(@ModelAttribute Member member) {

        memberService.saveMember(member);
        return "redirect:/list";
    }

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

    // 로그인 중인 유저 확인
    @GetMapping("/my-page")
    public String myPage(Authentication auth) {

        System.out.println(auth);
        System.out.println(auth.getName()); // 아이디 출력
        System.out.println(auth.isAuthenticated()); // 로그인 여부 출력
        System.out.println(auth.getAuthorities().contains(new SimpleGrantedAuthority("User")));
        return "mypage.html";
    }

}

/*
타임리프에서 유저 정보 출력 하는 법
<span sec:authentication="principal"></span>
<span sec:authentication="principal.username"></span>
<span sec:authentication="principal.authorities"></span>
<span sec:authorize="hasAuthority('일반유저')">특정권한이 있으면 보여주쇼</span>
<div sec:authorize="isAuthenticated()">
  로그인된 사람만 보여주쇼
</div>
 */
