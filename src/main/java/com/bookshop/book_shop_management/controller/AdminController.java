package com.bookshop.book_shop_management.controller;

import com.bookshop.book_shop_management.dto.request.AdminDto;
import com.bookshop.book_shop_management.entity.Admin;
import com.bookshop.book_shop_management.service.AdminService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/save")
    public Admin saveAdmin(@RequestBody AdminDto adminDto){
        return adminService.saveAdmin(adminDto);
    }
}
