package ru.zsa.msorder.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zsa.msorder.domain.OrderItem;


@Data
@NoArgsConstructor
public class OrderItemDTO {
    private String title;
    private int count;

    public OrderItemDTO(OrderItem orderItem) {
        this.title = orderItem.getProduct().getTitle();
        this.count = orderItem.getCount();
    }

}