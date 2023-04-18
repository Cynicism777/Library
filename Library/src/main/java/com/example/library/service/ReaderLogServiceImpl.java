package com.example.library.service;

import com.example.library.mapper.ReaderMapper;
import com.example.library.model.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReaderLogServiceImpl implements ReaderLogService{
    @Autowired
    private ReaderMapper readerMapper;
    @Override
    public Reader validateReader(String username, String password){
        return readerMapper.findByUsernameAndPassword(username,password);
    }
}
