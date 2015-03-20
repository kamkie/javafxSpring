package net.devops.javafxspring.backend.repository;

import net.devops.javafxspring.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);
}
