package com.book.service.impl;

import com.book.entity.Book;
import com.book.dao.BookDao;
import com.book.service.IBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Splitter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author k
 * @since 2023-06-23
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class BookServiceImpl extends ServiceImpl<BookDao, Book> implements IBookService {

    @Override
    public boolean deleteByIds(String ids) {
      Stream<String> stringStream = Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .splitToStream(ids);
        List<Integer> collect = stringStream.map(Integer::parseInt).collect(Collectors.toList());
        return this.removeByIds(collect);
    }

}
