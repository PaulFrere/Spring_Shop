package ru.zsa.msauth.dto;

import lombok.Data;
import ru.zsa.mscore.model.UserDeliveryAddressDto;

import java.util.List;

@Data
public class UserInfoDto {
    private String login;
    private String fio;
    private String phone;
    private String email;
    private List<UserDeliveryAddressDto> addresses;
}
