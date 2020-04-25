package fr.jbavril.restServer.service;

import java.util.Collection;

import fr.jbavril.restServer.model.User;
import fr.jbavril.restServer.security.BusinessResourceException;

public interface UserService {
	
	Collection<User> getAllUsers();
	
	User getUserById(Long id) throws BusinessResourceException;
	
	User findByLogin(String login) throws BusinessResourceException;
	
	User saveOrUpdateUser(User user) throws BusinessResourceException;
	
	void deleteUser(Long id) throws BusinessResourceException;
	
}
