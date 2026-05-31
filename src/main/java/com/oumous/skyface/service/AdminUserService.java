package com.oumous.skyface.service;

import com.oumous.skyface.entity.AdminUser;
import com.oumous.skyface.repository.AdminUserRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminUserService {

    @Autowired
    private AdminUserRepository repository;

    public List<AdminUser> findAll() {
        return repository.findAll();
    }

    public AdminUser findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public AdminUser save(AdminUser user) {
        if (user.getId() == null) {
            user.setCreatedDate(LocalDateTime.now());
        }
        return repository.save(user);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
