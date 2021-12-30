package com.example.idempotencytest.service.impl;

import com.example.idempotencytest.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author lqb
 * @date 2021/12/29 16:01
 */
@Service
@Slf4j
public class TokenServiceImpl implements TokenService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private static final String IDEMPOTENT_PREFIX = "idempotent:";

    @Override
    public String generateToken(String value) {
        String token = UUID.randomUUID().toString();
        String key = IDEMPOTENT_PREFIX + token;
        stringRedisTemplate.opsForValue().set(key,value,5, TimeUnit.MINUTES);
        return token;
    }

    @Override
    public boolean validToken(String token, String value) {
        // 设置 Lua 脚本，其中 KEYS[1] 是 key，KEYS[2] 是 value
        String script = "if redis.call('get', KEYS[1]) == KEYS[2] then return redis.call('del', KEYS[1]) else return 0 end";
        RedisScript<Long> redisScript = new DefaultRedisScript<>(script, Long.class);
        // 根据 Key 前缀拼接 Key
        String key = IDEMPOTENT_PREFIX + token;
        // 执行 Lua 脚本
        Long result = stringRedisTemplate.execute(redisScript, Arrays.asList(key, value));
        // 根据返回结果判断是否成功成功匹配并删除 Redis 键值对，若果结果不为空和0，则验证通过
        if (result != null && result != 0L) {
            log.info("验证 token={},key={},value={} 成功", token, key, value);
            return true;
        }
        log.info("验证 token={},key={},value={} 失败", token, key, value);
        return false;
    }

}
