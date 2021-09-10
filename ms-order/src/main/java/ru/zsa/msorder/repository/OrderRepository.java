package ru.zsa.msorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zsa.msorder.domain.Customer;
import ru.zsa.msorder.domain.Order;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
    Optional<Order> getOrderByCustomerAndStatus(Customer customer, String status);
    Optional<Order> getOrderByCustomer(Customer customer);
}
