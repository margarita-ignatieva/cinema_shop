package com.cinema.shop.dao;

import com.cinema.shop.model.Role;

public interface RoleDao {

    Role add(Role role);

    Role getRoleByName(String roleName);
}
