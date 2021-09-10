package ru.zsa.msdelivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zsa.msdelivery.model.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long> {
}
