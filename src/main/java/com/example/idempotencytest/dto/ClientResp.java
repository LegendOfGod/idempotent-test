package com.example.idempotencytest.dto;

import com.example.idempotencytest.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;

/**
 * @author lqb
 * @date 2021/12/29 11:36
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientResp<T> implements Serializable {
    private String code = "00";
    private String msg = "success";
    private T data;
}
