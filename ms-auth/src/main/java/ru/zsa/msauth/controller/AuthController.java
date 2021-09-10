package ru.zsa.msauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.zsa.msauth.domain.User;
import ru.zsa.msauth.dto.AuthRequestDTO;
import ru.zsa.msauth.dto.AuthResponseDTO;
import ru.zsa.msauth.dto.SignUpRequestDTO;
import ru.zsa.msauth.exeptions.MarketError;
import ru.zsa.msauth.services.UserService;
import ru.zsa.mscore.domain.UserInfo;
import ru.zsa.mscore.interfaces.ITokenService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private ITokenService tokenService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${kolumarket.jwt.expiration}")
    private String jwtExpiration;

    @Value("${server.servlet.context-path}")
    String path;

    @PostMapping("/register")
    public String registerUser(@RequestBody SignUpRequestDTO signUpRequest) {
        User user = new User();
        user.setPassword(signUpRequest.getPassword());
        user.setLogin(signUpRequest.getLogin());
        userService.saveUser(user);
        return "OK";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletResponse response, @RequestBody AuthRequestDTO request) {
        User user = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        if (user == null) return new ResponseEntity<>(new MarketError(HttpStatus.NOT_FOUND.value(), "Access denied"),HttpStatus.NOT_ACCEPTABLE);
        else {
            UserInfo userInfo = UserInfo.builder()
                    .userId(user.getId())
                    .role(user.getRole().getName())
                    .build();
            String token = tokenService.generateToken(userInfo);

            Cookie cookie = new Cookie("token", token.replace("Bearer ", ""));
            cookie.setPath(path);
            cookie.setMaxAge(tokenService.getJwtExpiration().intValue()*3600);
            response.addCookie(cookie);
            response.setContentType("text/plain");

            return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponseDTO(token));
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/loggedout")
    public ResponseEntity<?> loggedout(HttpServletResponse response) {
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        redisTemplate.opsForValue().setIfAbsent(userInfo.getToken(),"rejected", Duration.ofHours(Long.parseLong(jwtExpiration)));
        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);

        return ResponseEntity.status(HttpStatus.CREATED).body("logout");
    }
}
