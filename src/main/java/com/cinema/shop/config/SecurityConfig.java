package com.cinema.shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UserDetailsService userDetailsService,
                          PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationBuilder)
            throws Exception {
        authenticationBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/users/**")
                .hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,
                        "/cinema-halls/**",
                        "/movies/**",
                        "/movie-sessions/**").hasRole("ADMIN")
                .antMatchers("/orders/**",
                        "/shopping-carts/**").hasRole("USER")
                .antMatchers(HttpMethod.GET,
                        "/cinema-halls/**",
                        "/movies/**",
                        "/movie-sessions/available/**")
                .permitAll()
                .antMatchers(HttpMethod.POST,"/registration/**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }

}
