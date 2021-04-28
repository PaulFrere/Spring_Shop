package ru.zsa.msauth.dto;

import lombok.Data;

@Data
public class AuthRequestDTO {
    private String login;
    private String password;
}