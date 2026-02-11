package com.laa66.dao;

import com.laa66.model.Role;

public interface RoleDao {
    Role findByName(String name);
    Role findById(Integer id);
}