package ru.zsa.springshop.corelib.interfaces;


import ru.zsa.springshop.corelib.models.UserInfo;

public interface ITokenService {

    String generateToken(UserInfo user);

    UserInfo parseToken(String token);
}
