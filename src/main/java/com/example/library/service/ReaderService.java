package com.example.library.service;

import com.example.library.model.Reader;

import java.util.List;

public interface ReaderService {

    List<Reader> findAll();
    Reader findById(int id);
    Reader findByUsernameAndPassword(String username, String password);
    int insert(Reader reader);
    int update(Reader reader);
    int delete(int id);

}
