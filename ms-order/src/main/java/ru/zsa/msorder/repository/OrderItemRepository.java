package ru.zsa.msorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.zsa.msorder.domain.Order;
import ru.zsa.msorder.domain.OrderItem;
import ru.zsa.msproduct.model.Product;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem,OrderItem.Id>, JpaSpecificationExecutor<OrderItem> {
    Optional<OrderItem> getOrderItemByProductAndOrder(Product product, Order order);
    List<OrderItem> getOrderItemsByOrder(Order order);
}
