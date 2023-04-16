package com.example.library.service;

import com.example.library.model.Reader;
import com.example.library.mapper.ReaderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

@Service
public class ReaderServiceImpl implements ReaderService {
    @Autowired
    private ReaderMapper readerMapper;

    // 实现添加读者功能，返回boolean值，成功为true
    @Override
    public boolean addReader(Reader reader) {
        int insert = readerMapper.insert(reader);
        return insert > 0;
    }

    // 实现查询读者功能，根据输入的对象的属性值构造查询条件
    @Override
    public List<Reader> searchReaders(Reader reader) {
        QueryWrapper<Reader> wrapper = new QueryWrapper<>();
        if (reader.getId() != null) {
            wrapper.eq("id", reader.getId());
        }
        if (reader.getUsername() != null) {
            wrapper.like("username", reader.getUsername());
        }
        if (reader.getPassword() != null) {
            wrapper.like("password", reader.getPassword());
        }
        if (reader.getPenalty() != null) {
            wrapper.eq("penalty", reader.getPenalty());
        }
        // 使用构造好的查询条件执行查询，并返回查询结果
        return readerMapper.selectList(wrapper);
    }

    // 实现删除读者的功能
    @Override
    public void deleteReader(int readerId) {
        readerMapper.deleteById(readerId);
    }
}
