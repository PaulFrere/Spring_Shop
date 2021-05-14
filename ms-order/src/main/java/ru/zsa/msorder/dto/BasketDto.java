package ru.zsa.msorder.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zsa.msorder.domain.Basket;

@Data
@NoArgsConstructor
public class BasketDto {
    private Long id;
    private Integer productId;
    private Integer quantity;

    public BasketDto(Basket basketProduct) {
        this.id = basketProduct.getId();
        this.productId = basketProduct.getProductId();
        this.quantity = basketProduct.getQuantity();
    }
}
