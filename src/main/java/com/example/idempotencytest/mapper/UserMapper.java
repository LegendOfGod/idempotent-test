package com.example.idempotencytest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.idempotencytest.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lqb
 * @date 2021/12/29 11:12
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public User getUserById(String id);

    public void updateUser(User user);
}
