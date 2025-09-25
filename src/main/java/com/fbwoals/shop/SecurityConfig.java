package com.fbwoals.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable());
        // csrf 보안 끄는거
        // csrf 공격은 다른 사이트에서 우리 사이트를 몰래 이용할 수 있께 할 수 있는데 JWT 쓰면 ajax 요청 시 headers 에 서버에서 발금하는 랜덤문자 넣어서 확인 가능
        http.authorizeHttpRequests((authorize) ->
                authorize.requestMatchers("/**").permitAll()
        );
        // 로그인이 없어도 요청할 수 있는 URL 설정
        http.formLogin((formLogin) -> formLogin.loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/fail")
        );
        // 앞으로 폼으로 로그인 하겠다. 성공이나 실패시 이동할 페이지 url 설정 가능
        // springsecurity는 기본적으로 로그인 실패시 /login?error 로 이동
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
