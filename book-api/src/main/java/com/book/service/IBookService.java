package com.book.service;

import com.book.entity.Book;
import com.baomidou.mybatisplus.extension.service.IService;
import com.book.result.R;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author k
 * @since 2023-06-23
 */
public interface IBookService extends IService<Book> {
    /**
     * 删除
     *
     * @param ids id
     * @return {@link R}
     */
    boolean deleteByIds(String ids);
}
