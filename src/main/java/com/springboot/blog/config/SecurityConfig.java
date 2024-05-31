package com.springboot.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public WebSecurityCustomizer configure(){
        return (web -> web.ignoring()
                .requestMatchers(new AntPathRequestMatcher("/static/**"))
        );
    }
    // 특정 http요청에 대해서 웹기반으로 보안 구성
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
//                                        new AntPathRequestMatcher("/**")
                                new AntPathRequestMatcher("/user/signin"),
                                new AntPathRequestMatcher("/user/signup"),
                                new AntPathRequestMatcher("/user/logout")
                        ).permitAll()
                        .anyRequest().authenticated())
                .formLogin(formlogin-> formlogin
                        .loginPage("/user/signin")
                        .defaultSuccessUrl("/home")
                )
                .logout(logout->logout
                        .logoutSuccessUrl("/user/signin")
                        .invalidateHttpSession(true)
                )
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }
    // 암호화 관련 Bean등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
