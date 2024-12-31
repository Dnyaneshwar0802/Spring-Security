package com.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@EnableWebSecurity
@Component
public class SpringSecurityCustom {
    @Autowired
    MyUserDetailsService myUserDetailsService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("Inside security filter chain");
        http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests((auth) -> auth
                .requestMatchers("studentRestcontroller/saveData").permitAll().anyRequest().authenticated());
        http.formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    /*@Bean
    public UserDetailsService userDetailsService() {
        System.out.println("Start executing");
		*//*UserDetails user1 = User.withDefaultPasswordEncoder().username("xyz")
				.password("12345").build();
		UserDetails user2 = User.withDefaultPasswordEncoder().username("db").password("123").build();*//*
       *//* UserDetails user1 = User.builder().username("xyz").passwordEncoder((Function<String, String>) passwordEncoder()).password("12345").build();
        UserDetails user2 = User.builder().username("db").passwordEncoder((Function<String, String>) passwordEncoder()).password("123").build();*//*
        UserDetails user3 = User.builder().username("ab").passwordEncoder(a->"abc").build();
        return new InMemoryUserDetailsManager(user3);

    }*/
    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        System.out.println("going for userdetails");
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        return daoAuthenticationProvider;
    }
}
