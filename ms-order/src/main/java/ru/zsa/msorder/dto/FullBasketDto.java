package ru.zsa.msorder.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zsa.mscore.model.ProductBasketDto;

@Data
@NoArgsConstructor
public class FullBasketDto {
    private Long id;
    private ProductBasketDto product;
    private Integer quantity;
}
