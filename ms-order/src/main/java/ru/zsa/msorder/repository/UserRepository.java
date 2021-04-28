package ru.zsa.msorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zsa.msorder.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLogin(String login);

}
