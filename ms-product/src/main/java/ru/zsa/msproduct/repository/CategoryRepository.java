package ru.zsa.msproduct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zsa.msproduct.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
