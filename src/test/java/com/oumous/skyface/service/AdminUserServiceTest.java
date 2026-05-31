package com.oumous.skyface.service;

import com.oumous.skyface.entity.AdminUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminUserServiceTest {

    @Autowired
    private AdminUserService service;

    @Test
    void findAll_shouldReturnSeededUsers() {
        List<AdminUser> users = service.findAll();
        assertFalse(users.isEmpty());
    }

    @Test
    void findById_shouldReturnUser() {
        AdminUser saved = service.save(new AdminUser("svcfind", "svcfind@test.com", "USER", true));
        AdminUser found = service.findById(saved.getId());
        assertNotNull(found);
        assertEquals("svcfind", found.getUsername());
    }

    @Test
    void save_shouldSetCreatedDateOnNewUser() {
        AdminUser user = new AdminUser("svcnew", "svcnew@test.com", "ADMIN", true);
        AdminUser saved = service.save(user);
        assertNotNull(saved.getId());
        assertNotNull(saved.getCreatedDate());
    }

    @Test
    void save_shouldUpdateExistingUser() {
        AdminUser saved = service.save(new AdminUser("svcupdate", "before@test.com", "USER", true));
        saved.setEmail("after@test.com");
        service.save(saved);
        AdminUser updated = service.findById(saved.getId());
        assertEquals("after@test.com", updated.getEmail());
    }

    @Test
    void delete_shouldRemoveUser() {
        AdminUser saved = service.save(new AdminUser("svcdel", "svcdel@test.com", "USER", true));
        service.delete(saved.getId());
        assertNull(service.findById(saved.getId()));
    }
}
