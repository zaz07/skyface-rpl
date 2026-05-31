package com.oumous.skyface.bean;

import com.oumous.skyface.entity.AdminUser;
import com.oumous.skyface.service.AdminUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("view")
public class AdminUserBean {

    @Autowired
    private AdminUserService userService;

    private AdminUser selectedUser = new AdminUser();
    private List<AdminUser> users;

    public List<AdminUser> getUsers() {
        if (users == null) {
            users = userService.findAll();
        }
        return users;
    }

    public void prepareNew() {
        selectedUser = new AdminUser();
    }

    public void prepareEdit(AdminUser user) {
        selectedUser = user;
    }

    public void save() {
        selectedUser = userService.save(selectedUser);
        users = null;
    }

    public void delete(AdminUser user) {
        userService.delete(user.getId());
        users = null;
    }

    public AdminUser getSelectedUser() { return selectedUser; }
    public void setSelectedUser(AdminUser selectedUser) { this.selectedUser = selectedUser; }
}
