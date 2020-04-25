package fr.jbavril.restServer.service;

import java.util.Collection;
import java.util.Optional;

import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.jbavril.restServer.dao.UserRepository;
import fr.jbavril.restServer.model.User;
import fr.jbavril.restServer.security.BusinessResourceException;

//Annotation optionnelle car il n'existe qu'une seule implémentation
@Service(value ="userService")  //Déclare la classe comme un bean 
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Collection<User> getAllUsers() {		
		return IteratorUtils.toList(userRepository.findAll().iterator());
	}
	
	@Override
	public User getUserById(Long id) throws BusinessResourceException{
		Optional<User> optionalUser = userRepository.findById(id);
		return (optionalUser.isPresent())?optionalUser.get():null;
	}

	@Override
	public User findByLogin(String login) throws BusinessResourceException{
		try {
//			throw new Exception("Erreur de création ou de mise à jour de l'utilisateur :" + HttpStatus.INTERNAL_SERVER_ERROR);
			return userRepository.findByLogin(login);
		} catch(Exception e) {
			throw new BusinessResourceException("find By login", "Erreur de création ou de mise à jour de l'utilisateur :" + HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@Transactional(readOnly=false)
	public User saveOrUpdateUser(User user) {
		try {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			return userRepository.save(user);
		}catch(Exception ex) {
			throw new BusinessResourceException("Create or Update User Error", "Erreur de création ou de mise à jour de l'utilisateur :" + HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteUser(Long id) {
		try {
			userRepository.deleteById(id);
		} catch (Exception ex) {
			throw new BusinessResourceException("Delete User Error", "Erreur de création ou de mise à jour de l'utilisateur :" + HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
