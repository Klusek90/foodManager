package com.scorac.stockmanager.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authz -> authz
                        .requestMatchers("/users", "/reports", "/recipes").hasRole("ADMIN") // only for admin
                        .requestMatchers("/login", "/register").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form
//                        .loginPage("/login")
                        .permitAll())
                .logout(logout -> logout.permitAll());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
