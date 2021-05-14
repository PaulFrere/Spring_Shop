package ru.zsa.mscore.model;

import lombok.Data;

@Data
public class DeliveryInfoResponseDto {
    private ShopDto shop;
    private PickUpPointDto pickUpPoint;
    private UserDeliveryAddressDto userDeliveryAddress;
}
