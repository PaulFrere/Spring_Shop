package ru.zsa.mscore.model;

import lombok.Data;

@Data
public class UserDeliveryAddressDto {
    private String city;
    private String street;
    private String house;
    private String postalCode;
    private String apt;
    private String addInfo;
}
