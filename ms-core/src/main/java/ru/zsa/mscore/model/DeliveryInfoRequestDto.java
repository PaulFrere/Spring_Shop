package ru.zsa.mscore.model;

import lombok.Data;

@Data
public class DeliveryInfoRequestDto {
    private Integer deliveryType;
    private Long deliveryDetails;
}
