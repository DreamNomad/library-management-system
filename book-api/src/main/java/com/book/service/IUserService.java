package com.book.service;

import com.book.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.book.entity.dto.LoginDto;
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
public interface IUserService extends IService<User> {
    /**
     * 删除
     *
     * @param ids id
     * @return {@link R}
     */
    boolean deleteByIds(String ids);

    String login(LoginDto loginDto);

    boolean register(LoginDto loginDto);

    boolean logout(String token);

    boolean add(User user);

    boolean logout();
}
