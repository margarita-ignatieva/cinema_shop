package com.cinema.shop.service;

import com.cinema.shop.model.Role;

public interface RoleService {
    Role add(Role role);

    Role getRoleByName(String roleName);
}
