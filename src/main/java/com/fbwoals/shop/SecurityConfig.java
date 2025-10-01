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
                authorize
                        .requestMatchers("/login", "/signup", "/main.css").permitAll()
                        .anyRequest().authenticated() // 나머지 모든 요청은 인증 필요 -> 로그인 없이 접속 시도할 경우 자동으로 /login 리다이렉트
        );
        // 로그인이 없어도 요청할 수 있는 URL 설정
        http.formLogin((formLogin) -> formLogin.loginPage("/login")
                .defaultSuccessUrl("/list") // 로그인 성공 시 /list 로 이동
                // defaultSuccessUrl("/list", true) 으로 할 경우 로그인 후 무조건 /list 로 이동
                // 지금은 defalut 로 false 여서 예를 들어 /detail 에 접속하다가 로그인 페이지 리다이렉트 됐을 경우 로그인 후 /detail 로 이동
                .failureUrl("/login?error=true") // 로그인 실패 시 에러 파라미터와 함께 로그인 페이지로
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
