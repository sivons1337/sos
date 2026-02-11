package com.laa66.dao.impl;

import com.laa66.dao.RoleDao;
import com.laa66.model.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role findByName(String name) {
        TypedQuery<Role> query = entityManager.createQuery(
                "SELECT r FROM Role r WHERE r.name = :name", Role.class);
        query.setParameter("name", name);
        List<Role> results = query.getResultList();
        
        if (!results.isEmpty()) {
            return results.get(0);
        }
        return null; // Return null if not found
    }

    @Override
    public Role findById(Integer id) {
        return entityManager.find(Role.class, id);
    }
}