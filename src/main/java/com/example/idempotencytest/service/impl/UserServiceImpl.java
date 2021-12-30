package com.example.idempotencytest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.idempotencytest.entity.User;
import com.example.idempotencytest.mapper.UserMapper;
import com.example.idempotencytest.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lqb
 * @date 2021/12/29 11:11
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public void addUser(User user) {
        this.saveOrUpdate(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser1() {
        User user = this.baseMapper.getUserById("1476075457111138306");
        log.info("================>"+user.getUserName());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        user.setUserName("张三1");
        this.baseMapper.updateUser(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser2() {
        User user = this.baseMapper.getUserById("1476075457111138306");
        log.info("================>"+user.getUserName());
        user.setUserName("张三2");
        this.baseMapper.updateUser(user);
    }

}
