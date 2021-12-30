package com.example.idempotencytest.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.idempotencytest.entity.User;

/**
 * @author lqb
 * @date 2021/12/29 10:49
 */
public interface UserService extends IService<User> {

    void addUser(User user);

    void updateUser1();

    void updateUser2();
}
