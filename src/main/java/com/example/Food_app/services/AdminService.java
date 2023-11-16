package com.example.Food_app.services;

import com.example.Food_app.domain.Admin;
import com.example.Food_app.domain.SecuredUser;
import com.example.Food_app.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private SecuredUserService securedUserService;

    public void createAdmin(Admin admin){
        SecuredUser securedUser = admin.getSecuredUser();
        securedUser = securedUserService.save(securedUser, "ADMIN_USER");
        admin.setSecuredUser(securedUser);
        adminRepository.save(admin);
    }

    public Admin find(int adminId) {

        return adminRepository.findById(adminId).orElse(null);
    }
}

