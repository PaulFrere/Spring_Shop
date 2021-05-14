package ru.zsa.msdelivery.model;

import lombok.Data;

@Data
public class DeliveryPriceRequestDto {
    private String deliveryType;
    private Float totalPrice;
}
