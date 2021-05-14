package ru.zsa.msdelivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zsa.msdelivery.model.PickUpPoint;

public interface PickUpPointRepository extends JpaRepository<PickUpPoint, Long> {
}