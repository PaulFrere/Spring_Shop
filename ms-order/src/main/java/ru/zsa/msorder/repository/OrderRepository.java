package ru.zsa.msorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zsa.msorder.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
