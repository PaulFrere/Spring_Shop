package ru.zsa.mscore.filters;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.zsa.mscore.domain.UserInfo;
import ru.zsa.mscore.services.JWTTokenService;

import javax.servlet.FilterChain;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTTokenService tokenService;
    private final RedisTemplate redisTemplate;

    public JWTAuthenticationFilter(JWTTokenService tokenService, RedisTemplate redisTemplate) {
        this.tokenService = tokenService;
        this.redisTemplate = redisTemplate;
    }

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) {

        Cookie cookieJWT = null;

        if (httpServletRequest.getCookies()!=null) {
            cookieJWT = Arrays.stream(httpServletRequest.getCookies()).filter(cookie -> cookie.getName().equals("token")).findFirst().orElseGet(() -> {
                return null;
            });
        }

        if ((cookieJWT == null) || (redisTemplate.opsForValue().get(cookieJWT.getValue()) != null)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = createToken(cookieJWT.getValue());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private UsernamePasswordAuthenticationToken createToken(String authorizationToken) throws ExpiredJwtException {

        UserInfo userInfo = tokenService.parseToken(authorizationToken);

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (userInfo.getRole() != null && !userInfo.getRole().isEmpty()) {
            authorities.add(new SimpleGrantedAuthority(userInfo.getRole()));
        }

        return new UsernamePasswordAuthenticationToken(userInfo, null, authorities);
    }
}
