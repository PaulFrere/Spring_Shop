package ru.zsa.msorder.domain;

import lombok.Data;
import ru.zsa.msorder.dto.FullBasketDto;

import java.util.List;

@Data
public class OrderRequestDto {
    private Integer deliveryType;
    private Long deliveryDetails;
    private Double price;
    private Double deliveryPrice;
    private Long promoId;
    private List<FullBasketDto> items;
}
