package ru.zsa.msproduct.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zsa.msproduct.domain.Product;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private float cost;
    private String categoryName;

    public void ProductDTO(Product p) {
        this.id = p.getId();
        this.title = p.getTitle();
        this.price = p.getPrice();
        this.category = p.getCategory().getTitle();
    }
}
