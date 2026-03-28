package com.myownimplementation.todoapp.security;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.function.Function;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
public class SpringSecurityConfigurationClass {
    @Bean
    public InMemoryUserDetailsManager createInMemoryUserDetailsManager() {
        UserDetails user0 = createNewUser("Rahul","dummy");
        UserDetails user1 = createNewUser("Raj","dummy123");

        return new InMemoryUserDetailsManager(user0,user1);
    }

    private @NonNull UserDetails createNewUser(String username, String password) {
        Function<@Nullable String, @Nullable String> passwordEncoder =
                input -> passwordEncoder().encode(input);
        UserDetails user = User
                .builder()
                .passwordEncoder(passwordEncoder)
                .username(username)
                .password(password)
                .roles("USER", "ADMIN")
                .build();
        return user;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
        http.formLogin(withDefaults());
        http.csrf(csrf -> csrf.disable());
        http.headers(frame -> frame.frameOptions(withDefaults()).disable());
        return http.build();
    }
}
