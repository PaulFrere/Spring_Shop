package ru.zsa.springshop.msauth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zsa.springshop.msauth.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLogin(String login);

}