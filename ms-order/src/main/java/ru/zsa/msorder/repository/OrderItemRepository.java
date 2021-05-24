package ru.zsa.msorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.zsa.msorder.model.OrderItem;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {


    List<OrderItem> findAllByOrderId (Long id);

    @Query("select i from OrderItem i where i.orderId = ?1")
    List<OrderItem> findOrderItemsByOrderId (Long id);

    List<OrderItem> findAllByProductId(Long id);


}
