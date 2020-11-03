package com.cinema.shop.service;

import com.cinema.shop.lib.Service;
import com.cinema.shop.model.Role;
import com.cinema.shop.model.User;
import java.util.Set;
import javax.annotation.PostConstruct;

@Service
public class Injector {
    private final UserService userService;
    private final RoleService roleService;
    private final ShoppingCartService shoppingCartService;

    public Injector(UserService userService, RoleService roleService,
                    ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.roleService = roleService;
        this.shoppingCartService = shoppingCartService;
    }

    @PostConstruct
    private void injectData() {
        Role roleAdmin = Role.of("ADMIN");
        Role roleUser = Role.of("USER");
        roleService.add(roleAdmin);
        roleService.add(roleUser);
        User admin = new User();
        admin.setEmail("admin@mail.com");
        admin.setPassword("1234");
        admin.setRoles(Set.of(roleAdmin));
        User user = new User();
        user.setEmail("user@mail.com");
        user.setPassword("12345");
        user.setRoles(Set.of(roleUser));
        userService.add(admin);
        shoppingCartService.registerNewShoppingCart(admin);
        userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
    }
}
