package com.example.library.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.library.mapper.ReaderMapper;
import com.example.library.model.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderServiceImpl extends ServiceImpl<ReaderMapper,Reader> implements ReaderService{

    @Autowired
    private ReaderMapper readerMapper;

    @Override
    public List<Reader> findAll() {
        return readerMapper.findAll();
    }

    @Override
    public Reader findById(int id) {
        return readerMapper.findById(id);
    }

    @Override
    public Reader findByUsernameAndPassword(String username, String password) {
        return readerMapper.findByUsernameAndPassword(username, password);
    }

    @Override
    public int insert(Reader reader) {
        return readerMapper.insert(reader);
    }

    @Override
    public int update(Reader reader) {
        return readerMapper.update(reader);
    }

    @Override
    public int delete(int id) {
        return readerMapper.delete(id);
    }

}
