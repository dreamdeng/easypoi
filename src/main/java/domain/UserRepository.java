package domain;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.entity.User;


public interface UserRepository extends JpaRepository<User, Integer> {
}
