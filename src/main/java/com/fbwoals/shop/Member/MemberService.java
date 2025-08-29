package com.fbwoals.shop.Member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void saveMember(Member member) {

        var result = new Member();
        result.setUsername(member.getUsername());
        result.setPassword(new BCryptPasswordEncoder().encode(member.getPassword())); // 비밀번호 해싱
        result.setDisplayName(member.getDisplayName());
        memberRepository.save(result);
    }
}
