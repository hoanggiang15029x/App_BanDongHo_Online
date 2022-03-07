package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
	User findByUsername(String username);
}
