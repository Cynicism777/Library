package com.example.library.service;

import com.example.library.model.Reader;
import java.util.List;

public interface ReaderService {
    boolean addReader(Reader reader);
    List<Reader> searchReaders(Reader reader);
    void deleteReader(int readerId);
}