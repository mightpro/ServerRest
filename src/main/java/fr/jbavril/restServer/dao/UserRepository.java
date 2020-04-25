package fr.jbavril.restServer.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.jbavril.restServer.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByLogin(String login);
	
	Optional<User> findById(Long id);
	
}
