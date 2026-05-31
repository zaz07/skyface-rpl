package com.oumous.skyface.repository;

import com.oumous.skyface.entity.AdminUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Sql(statements = "INSERT INTO admin_users (username, email, role, active, created_date) VALUES ('seed', 'seed@test.com', 'USER', true, NOW())", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class AdminUserRepositoryTest {

    @Autowired
    private AdminUserRepository repository;

    @Test
    void findAll_shouldReturnSeededUsers() {
        List<AdminUser> users = repository.findAll();
        assertFalse(users.isEmpty());
    }

    @Test
    void findById_shouldReturnUser() {
        AdminUser saved = repository.save(new AdminUser("findme", "find@test.com", "ADMIN", true));
        Optional<AdminUser> found = repository.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("findme", found.get().getUsername());
    }

    @Test
    void save_shouldPersistUser() {
        AdminUser user = new AdminUser("newuser", "new@test.com", "USER", false);
        AdminUser saved = repository.save(user);
        assertNotNull(saved.getId());
        assertEquals("newuser", saved.getUsername());
    }

    @Test
    void delete_shouldRemoveUser() {
        AdminUser saved = repository.save(new AdminUser("todelete", "del@test.com", "USER", true));
        repository.deleteById(saved.getId());
        assertFalse(repository.findById(saved.getId()).isPresent());
    }
}
