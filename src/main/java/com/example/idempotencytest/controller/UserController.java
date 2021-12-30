package com.example.idempotencytest.controller;

import com.example.idempotencytest.dto.ClientResp;
import com.example.idempotencytest.entity.User;
import com.example.idempotencytest.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lqb
 * @date 2021/12/29 11:28
 */
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/addUser")
    public ClientResp<Void> addUser(@RequestBody User user){
        userService.addUser(user);
        return new ClientResp<>();
    }

    @GetMapping("updateUser1")
    public ClientResp<Void> updateUser1(){
        userService.updateUser1();
        return new ClientResp<>();
    }

    @GetMapping("updateUser2")
    public ClientResp<Void> updateUser2(){
        userService.updateUser2();
        return new ClientResp<>();
    }
}
