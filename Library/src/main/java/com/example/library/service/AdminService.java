package com.example.library.service;

import com.example.library.model.Admin;

public interface AdminService {

    Admin validateAdmin(String admin, String password);

}
