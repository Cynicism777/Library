package com.example.library.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.library.model.Reader;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ReaderMapper extends BaseMapper<Reader> {
    List<Reader> findAll();
    Reader findById(int id);
    Reader findByUsernameAndPassword(String username, String password);
    int insert(Reader reader);
    int update(Reader reader);
    int delete(int id);
}
