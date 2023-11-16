package com.example.Food_app.controller;

import com.example.Food_app.dtos.CreateAdminRequest;
import com.example.Food_app.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/admin/create")
    public void createAdmin(@RequestBody @Valid CreateAdminRequest createAdminRequest){
        adminService.createAdmin(createAdminRequest.toAdmin());
    }
}
