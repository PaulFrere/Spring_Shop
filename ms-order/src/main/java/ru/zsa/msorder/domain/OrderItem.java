package ru.zsa.msorder.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zsa.msorder.dto.FullBasketDto;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
@Data
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_order")
    private Order order;

    @Column(name = "id_product")
    private Integer productId;

    @Column
    private Integer quantity;

    @Column(name = "price_per_product")
    private Double pricePerProduct;

    public OrderItem(FullBasketDto fullBasketDto) {
        this.productId = fullBasketDto.getProduct().getId();
        this.quantity = fullBasketDto.getQuantity();
        this.pricePerProduct = fullBasketDto.getProduct().getPrice();
    }
}

