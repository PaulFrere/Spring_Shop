package ru.zsa.mscore.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import ru.zsa.mscore.model.TokenRedis;

import javax.annotation.PostConstruct;
import java.time.Duration;


@Repository
public class RedisRepositoryImpl implements RedisRepository {
    private String token;
    
    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;
    private ListOperations listOperations;
    
    @Autowired
    public RedisRepositoryImpl(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init(){
        hashOperations = redisTemplate.opsForHash();
        listOperations = redisTemplate.opsForList();
    }

    public void addToken(TokenRedis tokenRedis) {

        redisTemplate.opsForValue().set(tokenRedis.getTokenRedis(),tokenRedis.getTokenRedis(), Duration.ofSeconds(15, 0));
    }


    public void add(String token){
        listOperations.leftPush("token0", token);
    }

    public String findRedisToken(String token){
        System.out.println(hashOperations.get(token, token));
        return (String) hashOperations.get(token, token);
    }

}
