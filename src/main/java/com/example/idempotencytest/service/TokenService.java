package com.example.idempotencytest.service;

/**
 * @author lqb
 * @date 2021/12/29 15:57
 */
public interface TokenService {

    public String generateToken(String value);
    public boolean validToken(String token, String value);
}
