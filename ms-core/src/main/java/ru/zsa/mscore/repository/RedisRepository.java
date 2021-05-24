package ru.zsa.mscore.repository;


import ru.zsa.mscore.model.TokenRedis;

public interface RedisRepository {

    void addToken(TokenRedis tokenRedis);

    void add(String token);

    String findRedisToken(String token);
    
}
