package ru.zsa.msstorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zsa.msstorage.domain.Storage;

public interface StorageRepository extends JpaRepository<Storage,Long> {
}
