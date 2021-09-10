package ru.zsa.msauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zsa.msauth.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLogin(String login);

}