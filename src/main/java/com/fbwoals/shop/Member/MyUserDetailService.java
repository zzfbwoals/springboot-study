package com.fbwoals.shop.Member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var result = memberRepository.findById(username);
        if(result.isEmpty()) throw new UsernameNotFoundException("아이디 없음");
        var user = result.get();

        // 유저 권한 설정
        // 권한에 따라 유저들을 분류하기 위함
        // if 문을 통해 관리자, 판매자 들의 역할도 구분해서 부여 가능
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("User"));
        CustomUser customUser = new CustomUser(user.getUsername(), user.getPassword(), authorities);
        customUser.displayName = user.getDisplayName();
        return customUser;
    }

}

/*
- 유저가 폼으로 아이디/비번 제출시 비번 맞는지 검사 같은 것도 자동으로 해주는데
- 근데 비번 검사하려면 DB에 있던 비번도 필요하지 않겠습니까 근데 라이브러리는 비번이 DB어디에 있는지 모름
- 그 DB에 있던 비번 찾아오는건 여러분들이 직접 코드로 짜줘야하는데 그거 작성하는 곳이 방금 만든 MyUserDetailsService 클래스라고 생각하면 됩니다.
- 그 클래스에서 loadUserByUsername() 함수만들고 거기서 아이디/비번/권한 이런걸 담아서 return 해주면 비번검사 등이 알아서 됩니다.
 */