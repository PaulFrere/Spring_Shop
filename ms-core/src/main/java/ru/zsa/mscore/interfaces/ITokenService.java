package ru.zsa.mscore.interfaces;

import ru.zsa.mscore.model.UserInfo;

public interface ITokenService {

    String generateToken(UserInfo user);

    UserInfo parseToken(String token);

    String findRedisToken(String redistoken);

}