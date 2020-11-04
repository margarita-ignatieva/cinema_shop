package com.cinema.shop.security;

import com.cinema.shop.model.User;
import com.cinema.shop.service.UserService;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User with " + email + "email, not found"));
        UserBuilder builder =
                    org.springframework.security.core.userdetails.User.withUsername(email);
        builder.password(user.getPassword());
        String[] roles = user.getRoles().stream()
                    .map(role -> role.getRoleName().toString())
                    .toArray(String[]::new);
        builder.roles(roles);
        return builder.build();
    }
}
