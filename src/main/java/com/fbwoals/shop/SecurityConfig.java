package com.fbwoals.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
        return http.build();
    }

}
