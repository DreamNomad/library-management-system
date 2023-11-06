package com.book;

import com.book.entity.Book;
import com.book.service.IBookService;
import com.book.utils.JWTUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestBook {

    @Autowired
    private IBookService bookService;

    @Test
    public void insert(){
        bookService.save(new Book().setDesc("222").setImagePath("111"));
    }

}
