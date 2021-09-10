package ru.zsa.msorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.zsa.msorder.domain.Customer;
import ru.zsa.msorder.domain.User;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long>, JpaSpecificationExecutor<Customer> {
    Optional<Customer> getCustomerBySessionId(String sessionId);
    Optional<Customer> getCustomerByUser(User user);
}
