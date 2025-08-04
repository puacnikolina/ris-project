package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http.authorizeHttpRequests(requests -> requests
	                    .requestMatchers("/admin/**").hasRole("ADMIN")
	                    .requestMatchers("/user/**").hasRole("USER")
	                    .requestMatchers("/**").permitAll()
	                    .anyRequest().authenticated())
	            .formLogin(form -> form
	                    .loginPage("/auth/loginPage").permitAll()
	                    .loginProcessingUrl("/auth")
	                    .defaultSuccessUrl("/auth/default",true)
	                    .failureForwardUrl("/index.jsp"))
	            
	            .exceptionHandling(handling -> handling.accessDeniedPage("/index.jsp"))
	            .csrf(csrf -> csrf.disable());

	    return http.build();
	}


    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	    return config.getAuthenticationManager();
	}


    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}