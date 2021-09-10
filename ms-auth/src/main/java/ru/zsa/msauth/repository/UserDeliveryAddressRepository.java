package ru.zsa.msauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zsa.msauth.domain.UserDeliveryAddress;

public interface UserDeliveryAddressRepository extends JpaRepository<UserDeliveryAddress, Long> {
}
