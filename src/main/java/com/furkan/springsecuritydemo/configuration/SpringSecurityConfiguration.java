package com.furkan.springsecuritydemo.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.net.http.HttpClient;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails arda = User.builder()
                .username("arda")
                .password("{noop}1234")
                .roles("EMPLOYEE")
                .build();

        UserDetails veli = User.builder()
                .username("veli")
                .password("{noop}1234")
                .roles("EMPLOYEE","MANAGER")
                .build();

        UserDetails furkan = User.builder()
                .username("furkan")
                .password("{noop}1234")
                .roles("EMPLOYEE","MANAGER","ADMIN")
                .build();

        return new InMemoryUserDetailsManager(arda, veli, furkan);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests( configurer ->
                        configurer
                                .requestMatchers("/").hasAnyRole("EMPLOYEE")
                                .requestMatchers("/leaders/**").hasAnyRole("MANAGER")
                                .requestMatchers("/systems/**").hasAnyRole("ADMIN")
                                .anyRequest().authenticated())
                .exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/access-denied"))
                .formLogin(form -> form
                        .loginPage("/showMyLoginPage")
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll()
                ).logout(logout -> logout.permitAll());
        return http.build();
    }



}
