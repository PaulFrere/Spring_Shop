package ru.zsa.msauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zsa.msauth.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);

}
