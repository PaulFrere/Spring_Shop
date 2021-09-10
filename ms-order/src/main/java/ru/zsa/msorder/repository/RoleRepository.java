package ru.zsa.msorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zsa.msorder.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);

}
