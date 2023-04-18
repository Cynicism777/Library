package com.example.library.service;

import com.example.library.model.Reader;

public interface ReaderLogService {
    Reader validateReader(String username, String password);
}
