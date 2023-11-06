package com.book.controller;

import com.book.context.UserContext;
import com.book.entity.User;
import com.book.entity.dto.LoginDto;
import com.book.result.R;
import com.book.result.ResultCodeEnum;
import com.book.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
public class UserController {
    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public R<String> login(@Validated @RequestBody LoginDto loginDto) {
        // TODO 也可选择使用自带的ResponseEntity
        return R.ok(userService.login(loginDto)).setMessage("登录成功");
    }

    @PostMapping("/register")
    public R register(@Validated @RequestBody LoginDto loginDto) {
        return R.isOk(userService.register(loginDto)).setMessage("注册成功");
    }

    @GetMapping("/logout")
    public R logout() {
        return R.setResult(ResultCodeEnum.TOKEN_EXPIRED).setMessage("注销成功");
    }

    @GetMapping("/user")
    public R<List<User>> list() {
        return R.ok(userService.list());
    }

    @GetMapping("/user/{id}")
    public R<User> list(@PathVariable(name = "id") Integer id) {
        return R.ok(userService.getById(id));
    }

    @DeleteMapping("/user/{ids}")
    public R delete(@PathVariable(name = "ids") String ids) {
        return R.isDeleteOk(userService.deleteByIds(ids));
    }

    @PostMapping("/user")
    public R insert(@Validated @RequestBody User user){
        return R.isInsertOk(userService.add(user));
    }

    @PutMapping("/user")
    public R update(@RequestBody User user){
        return R.isUpdateOk(userService.updateById(user));
    }

    @GetMapping("/get-user")
    public R<User> getUser(){
        return R.ok(UserContext.getUser());
    }
}
