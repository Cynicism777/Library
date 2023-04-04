package com.example.library.service;

import com.example.library.mapper.AdminMapper;
import com.example.library.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminMapper adminMapper;

    // 实现验证Admin的功能
    @Override
    public Admin validateAdmin(String admin, String password) {
        return adminMapper.findByAdminAndPassword(admin, password);
    }


}
