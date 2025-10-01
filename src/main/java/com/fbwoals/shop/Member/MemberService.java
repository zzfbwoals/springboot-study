package com.fbwoals.shop.Member;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 현재는 회원 저장만 하는데 회원 저장하고 이메일 발송이나 포인트 지급과 같은 여러 기능(작업)이 필요할 때는
    // @Transactional 어노테이션을 사용해서 롤백 가능하게 설정
    public void saveMember(Member member) {

        // 비즈니스 로직 검증 - 사용자에게 보이는 예외 처리
        if(member.getUsername() == null || member.getUsername().isEmpty()) {
            throw new RuntimeException("아이디가 없습니다.");
        }
        if(member.getUsername().length() > 20) {
            throw new RuntimeException("아이디는 20자 이하로 입력해주세요.");
        }
        if(member.getPassword() == null || member.getPassword().isEmpty()) {
            throw new RuntimeException("비밀번호가 없습니다.");
        }
        if(member.getPassword().length() < 10) {
            throw new RuntimeException("비밀번호는 10자 이상이어야 합니다.");
        }
        if(memberRepository.existsById(member.getUsername())) {
            throw new RuntimeException("이미 존재하는 아이디입니다.");
        }

        var result = new Member();
        result.setUsername(member.getUsername());
        // result.setPassword(new BCryptPasswordEncoder().encode(member.getPassword())); // 비밀번호 해싱
        // 매번 new 하는 것 보다 빈으로 등록해서 재사용하면 효율적
        result.setPassword(passwordEncoder.encode(member.getPassword()));
        result.setDisplayName(member.getDisplayName());

        // DB 제약조건으로 작성한 것과 비슷한 검증 로직을 작성해야 함.
        // @Id @Column 등의 제약조건을 사용하면 자동으로 검증을 해줌
        // 아래는 검증 후 오류를 발생시키는 예시
        try {
            memberRepository.save(result);
        } catch (DataIntegrityViolationException e) {
            // 동시성으로 인한 예외적 상황
            throw new RuntimeException("이미 존재하는 아이디입니다.");
        }
    }

}
