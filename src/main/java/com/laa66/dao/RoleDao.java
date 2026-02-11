package com.laa66.dao;

import com.laa66.model.Role;
import java.util.List;

public interface RoleDao {
    Role createRole(String name);

    Role getRole(Integer roleId);

    List<Role> getAllRoles();

    Role updateRole(Integer roleId, String name);

    void deleteRole(Integer roleId);

    Role getRoleByName(String name);
}