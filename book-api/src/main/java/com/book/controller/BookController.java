package com.book.controller;

import com.book.entity.Book;
import com.book.entity.User;
import com.book.result.R;
import com.book.service.IBookService;
import com.book.service.IUserService;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.errorprone.annotations.Var;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author k
 * @since 2023-06-23
 */
@Slf4j
@RestController
public class BookController {
    private final IBookService bookService;

    @Autowired
    public BookController(IBookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book")
    public R<List<Book>> list() {
        return R.ok(bookService.list());
    }

    @GetMapping("/book/{id}")
    public R<Book> list(@PathVariable(name = "id") Integer id) {
        return R.ok(bookService.getById(id));
    }

    @DeleteMapping("/book/{ids}")
    public R delete(@PathVariable(name = "ids")String ids) {
        return R.isOk(bookService.deleteByIds(ids));
    }

    @PostMapping("/book")
    public R insert(@Validated @RequestBody Book book){
        //ResponseEntity.ok().header()
        return R.isOk(bookService.save(book));
    }

    @PutMapping("/book")
    public R update(@RequestBody Book book){
        return R.isOk(bookService.updateById(book));
    }
}
