package ru.zsa.mscore.interfaces;

import ru.zsa.mscore.domain.UserInfo;

public interface ITokenService {

    String generateToken(UserInfo user);
    UserInfo parseToken(String token);
    Long getJwtExpiration();
}
