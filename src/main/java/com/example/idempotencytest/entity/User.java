package com.example.idempotencytest.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lqb
 * @date 2021/12/29 10:50
 */
@Data
@TableName("t_user")
public class User implements Serializable {

    private static final long serialVersionUID = -4376844858306484495L;

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String userName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date birthday;

}
