package com.example.demo.configurations;

import com.example.demo.entity.RoleEntity;
import com.example.demo.filters.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@RequiredArgsConstructor
public class WebSecurityConfiguration {
    private final JwtTokenFilter jwtTokenFilter;
    @Value("${api.prefix}")
    private String apiPrefix;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(requests -> {
                    requests
                            .requestMatchers(
                                    String.format("%s/users/register", apiPrefix),
                                    String.format("%s/users/login", apiPrefix)
                            )
                            .permitAll()
                            .requestMatchers(GET,
                                    String.format("%s/categories**", apiPrefix)).hasAnyRole(RoleEntity.USER, RoleEntity.ADMIN)

                            .requestMatchers(POST,
                                    String.format("%s/categories/**", apiPrefix)).hasAnyRole(RoleEntity.ADMIN)
                            .requestMatchers(PUT,
                                    String.format("%s/categories/**", apiPrefix)).hasAnyRole(RoleEntity.ADMIN)
                            .requestMatchers(DELETE,
                                    String.format("%s/categories/**", apiPrefix)).hasAnyRole(RoleEntity.ADMIN)
                            .requestMatchers(GET,
                                    String.format("%s/products**", apiPrefix)).hasAnyRole(RoleEntity.USER, RoleEntity.ADMIN)
                            .requestMatchers(POST,
                                    String.format("%s/products/**", apiPrefix)).hasAnyRole(RoleEntity.ADMIN)
                            .requestMatchers(PUT,
                                    String.format("%s/products/**", apiPrefix)).hasAnyRole(RoleEntity.ADMIN)
                            .requestMatchers(DELETE,
                                    String.format("%s/products/**", apiPrefix)).hasAnyRole(RoleEntity.ADMIN)
                            .requestMatchers(POST,
                                    String.format("%s/orders/**", apiPrefix)).hasAnyRole(RoleEntity.USER)
                            .requestMatchers(GET,
                                    String.format("%s/orders/**", apiPrefix)).hasAnyRole(RoleEntity.USER, RoleEntity.ADMIN)
                            .requestMatchers(PUT,
                                    String.format("%s/orders/**", apiPrefix)).hasRole(RoleEntity.ADMIN)
                            .requestMatchers(DELETE,
                                    String.format("%s/orders/**", apiPrefix)).hasRole(RoleEntity.ADMIN)
                            .requestMatchers(POST,
                                    String.format("%s/order_details/**", apiPrefix)).hasAnyRole(RoleEntity.USER)
                            .requestMatchers(GET,
                                    String.format("%s/order_details/**", apiPrefix)).hasAnyRole(RoleEntity.USER, RoleEntity.ADMIN)
                            .requestMatchers(PUT,
                                    String.format("%s/order_details/**", apiPrefix)).hasRole(RoleEntity.ADMIN)
                            .requestMatchers(DELETE,
                                    String.format("%s/order_details/**", apiPrefix)).hasRole(RoleEntity.ADMIN)
                            .anyRequest().authenticated();
                })

        ;
        return http.build();
    }
}
