package com.group6.cenapp.services;

import com.group6.cenapp.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> getAllRoles();
    Optional<Role> getRoleById(Integer id);
    Role saveRole(Role role);
    Role updateRole(Role role);
    void deleteRoleById(Integer id);
}
