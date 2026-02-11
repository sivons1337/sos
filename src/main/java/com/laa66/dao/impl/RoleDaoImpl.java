package com.laa66.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

import com.laa66.dao.RoleDao;
import com.laa66.model.Role;

@Transactional
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role createRole(String name) {
        Role role = new Role();
        role.setName(name);

        entityManager.persist(role);
        return role;
    }

    @Override
    public Role getRole(Integer roleId) {
        return entityManager.find(Role.class, roleId);
    }

    @Override
    public List<Role> getAllRoles() {
        TypedQuery<Role> query = entityManager.createQuery("SELECT r FROM Role r", Role.class);
        return query.getResultList();
    }

    @Override
    public Role updateRole(Integer roleId, String name) {
        Role role = entityManager.find(Role.class, roleId);
        if (role != null) {
            role.setName(name);
            entityManager.merge(role);
        }
        return role;
    }

    @Override
    public void deleteRole(Integer roleId) {
        Role role = entityManager.find(Role.class, roleId);
        if (role != null) {
            entityManager.remove(role);
        }
    }

    @Override
    public Role getRoleByName(String name) {
        TypedQuery<Role> query = entityManager.createQuery(
                "SELECT r FROM Role r WHERE r.name = :name", Role.class);
        query.setParameter("name", name);
        List<Role> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }
}