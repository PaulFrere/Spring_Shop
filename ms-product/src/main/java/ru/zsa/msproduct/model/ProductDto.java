package ru.zsa.msproduct.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private float cost;
    private String categoryName;

    private Long categoryId;
}
