package com.example.MoviesSystem.security;

import com.example.MoviesSystem.common.GlobalConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz
                .requestMatchers("/movies/create", "/movies/update/**", "/movies/delete/**")
                    .hasAuthority(GlobalConstants.ADMIN_ROLE)
                .requestMatchers("/movies", "/accessDenied", "/auth/login", "/auth/register")
                    .permitAll()
            )
           .formLogin(f -> f
                .loginPage("/auth/login")
                .defaultSuccessUrl("/movies")
                .loginProcessingUrl("/auth/login")
                .failureUrl("/auth/login?error=true")
           ).logout(l -> l
                        .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout")).permitAll())
                .exceptionHandling(e -> e.accessDeniedPage("/accessDenied"));

        return http.build();
    }


    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

}

