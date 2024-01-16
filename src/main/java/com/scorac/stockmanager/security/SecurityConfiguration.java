package com.scorac.stockmanager.security;

import com.scorac.stockmanager.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final UserService userService;

    public SecurityConfiguration(UserService userService) {
        this.userService = userService;
    }


//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        //creating user user
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("user")
//                .roles("USER")
//                .build();
//        // user admin
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("admin")
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/users", "/reports", "/recipes").hasRole("ADMIN") // only for admin
                        .requestMatchers("/error").permitAll()   //for all
                        .anyRequest().authenticated()   //rest that required authentication
                )
                .formLogin((form) -> form
//                        .loginPage("/login")  //login page
                        .permitAll()
                )
                .userDetailsService(userService)
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

}
