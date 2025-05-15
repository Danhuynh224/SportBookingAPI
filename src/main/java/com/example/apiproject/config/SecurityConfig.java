package com.example.apiproject.config;

import com.example.apiproject.JWT.JwtFilter;
import com.example.apiproject.entity.Account;
import com.example.apiproject.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    @Lazy  // Thêm @Lazy vào để trì hoãn khởi tạo JwtFilter
    private JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(AccountRepository accountRepo) {
        return username -> {
            Account acc = accountRepo.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Not found"));
            return new org.springframework.security.core.userdetails.User(
                    acc.getUsername(),
                    acc.getPassword(),
                    List.of(new SimpleGrantedAuthority("ROLE_USER"))
            );
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Tắt CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**", "/sportsfacilities/**", "/subfacilities/**","/post/**", "/images/**").permitAll()  // Cho phép tất cả yêu cầu đến /auth/login
                        .anyRequest().authenticated()  // Yêu cầu xác thực cho các yêu cầu khác
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // Không dùng session

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // Thêm filter JWT

        return http.build();
    }


}
