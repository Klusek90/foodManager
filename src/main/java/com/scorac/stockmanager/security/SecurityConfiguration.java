package com.scorac.stockmanager.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
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
                        //endpoint access for all type of users
                        .requestMatchers("/loginPage.css", "/img/logo.jpg").permitAll()
                        //resticting accress for admin users
                        .requestMatchers("/users/*", "/recipes/*").hasRole("ADMIN") // only for admin
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        //overriding default page with custom login page
                        .loginPage("/login")
                        .permitAll())
                .logout(logout -> logout.permitAll());
        return http.build();
    }

    @Bean
    //password encryption
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
