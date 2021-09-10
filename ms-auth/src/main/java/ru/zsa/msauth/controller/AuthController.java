package ru.zsa.msauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.zsa.msauth.entities.User;
import ru.zsa.msauth.services.UserService;
import ru.zsa.mscore.interfaces.ITokenService;
import ru.zsa.mscore.model.TokenRedis;
import ru.zsa.mscore.model.UserInfo;
import ru.zsa.mscore.model.dto.AuthRequestDto;
import ru.zsa.mscore.model.dto.AuthResponseDto;
import ru.zsa.mscore.model.dto.SignUpRequestDto;
import ru.zsa.mscore.repository.RedisRepository;
import ru.zsa.router.feignclients.ProductFeignClient;


import javax.servlet.http.HttpServletRequest;


@RestController
public class AuthController {

    @Autowired
    private ProductFeignClient productFeignClient;

    @Autowired
    private UserService userService;

    @Autowired
    private ITokenService tokenService;

    @Autowired
    private RedisRepository redisRepository;

    @RequestMapping("/helloproduct")
    public String hello(){
        return   productFeignClient.hello();
    }

    @PostMapping("/signup")
    public String signUp(@RequestBody SignUpRequestDto signUpRequest) {
        User user = new User();
        user.setPassword(signUpRequest.getPassword());
        user.setLogin(signUpRequest.getLogin());
        userService.saveUser(user);
        return "OK";
    }

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody AuthRequestDto request) {
        User user = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        System.out.println("login " + request.getLogin());
        System.out.println("password " + request.getPassword());
        UserInfo userInfo = UserInfo.builder()
                .userId(user.getId())
                .userEmail(user.getLogin())
                .role(user.getRole().getName())
                .build();
        String token = tokenService.generateToken(userInfo);
        return new AuthResponseDto(token);
    }

    @GetMapping("/log_out")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String logout (HttpServletRequest httpServletRequest){
        String authorizationHeadertoken = httpServletRequest.getHeader("Authorization");
        redisRepository.addToken(new TokenRedis(authorizationHeadertoken, 10000L));
        System.out.println(authorizationHeadertoken);
        return authorizationHeadertoken;
    }

    @GetMapping("/check")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String check() {
        return "OK!";
    }

}
