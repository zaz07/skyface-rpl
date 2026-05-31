package com.oumous.skyface.bean;

import com.oumous.skyface.entity.AdminUser;
import com.oumous.skyface.service.AdminUserService;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("view")
public class DashboardBean {

    @Autowired
    private AdminUserService userService;

    private List<AdminUser> users;

    @PostConstruct
    public void init() {
        users = userService.findAll();
    }

    public long getTotalUsers() {
        return getUsers().size();
    }

    public long getActiveUsers() {
        return getUsers().stream().filter(AdminUser::isActive).count();
    }

    public long getInactiveUsers() {
        return getUsers().stream().filter(u -> !u.isActive()).count();
    }

    public List<AdminUser> getRecentUsers() {
        return getUsers().stream()
            .sorted((a, b) -> b.getCreatedDate().compareTo(a.getCreatedDate()))
            .limit(5)
            .collect(Collectors.toList());
    }

    public List<AdminUser> getUsers() {
        return users;
    }
}
