package com.libraryland.services;

import com.libraryland.entities.Book;
import com.libraryland.repositories.BaseRepository;
import com.libraryland.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl extends BaseServiceImpl<Book, Long> implements BookService {
    @Autowired
    private BookRepository bookRepository;

    public BookServiceImpl(BaseRepository<Book, Long> baseRepository) {
        super(baseRepository);
    }
}