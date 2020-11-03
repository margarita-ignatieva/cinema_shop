package com.cinema.shop.service.impl;

import com.cinema.shop.dao.RoleDao;
import com.cinema.shop.model.Role;
import com.cinema.shop.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roledao;

    public RoleServiceImpl(RoleDao roledao) {
        this.roledao = roledao;
    }

    @Override
    public Role add(Role role) {
        return roledao.add(role);
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roledao.getRoleByName(roleName);
    }
}
