package ru.zsa.msorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zsa.msorder.domain.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
