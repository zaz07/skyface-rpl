package com.oumous.skyface.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/login.xhtml", "/javax.faces.resource/**", "/error/**", "/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login.xhtml")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/index.xhtml")
                .failureUrl("/login.xhtml?error=true")
                .permitAll()
                .and()
            .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login.xhtml")
                .permitAll()
                .and()
            .csrf().disable()
            .headers().frameOptions().sameOrigin();
        return http.build();
    }

    @Bean
    public UserDetailsService users() {
        var admin = User.builder()
            .username("admin")
            .password("{noop}admin")
            .roles("ADMIN")
            .build();
        var user = User.builder()
            .username("user")
            .password("{noop}user")
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(admin, user);
    }
}
