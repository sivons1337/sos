package com.laa66.util;

import com.laa66.dao.RoleDao;
import com.laa66.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private RoleDao roleDao;
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void run(String... args) throws Exception {
        createRoleIfNotExists("STUDENT");
        createRoleIfNotExists("ADMIN");
        createRoleIfNotExists("LECTURER");
    }

    private void createRoleIfNotExists(String roleName) {
        try {
            Role existingRole = roleDao.findByName(roleName);
            if (existingRole == null) {
                Role role = new Role();
                role.setName(roleName);
                entityManager.persist(role);
                entityManager.flush();
                System.out.println("Created role: " + roleName);
            } else {
                System.out.println("Role already exists: " + roleName);
            }
        } catch (Exception e) {
            System.out.println("Error checking/creating role: " + roleName + ", Error: " + e.getMessage());
        }
    }
}