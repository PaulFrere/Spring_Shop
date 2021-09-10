package ru.zsa.msauth.dto;

import lombok.Data;

@Data
public class SignUpRequestDTO {

    private String login;

    private String password;
}
