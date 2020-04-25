package fr.jbavril.restServer.dao;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import fr.jbavril.restServer.model.User;


//@RunWith(SpringRunner.class) //A ajouté en JUnit4, permet d faire la liaison entre junit et spring
@DataJpaTest //fournit une configuration intégrée de la BDD h2, hibernate, springdata et datasource
//Permet la détection des entités annotées par Entity et intègre la gestion des logs SQL
public class UserRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private UserRepository userRepository; //Permet de persister en bdd lors données lors des tests.
	
	User user = new User("Dupont", "password", 1);
	
	private boolean executed = false;
	
	@BeforeEach
	public void setup() {
		if(!executed) {
			entityManager.persist(user);
			entityManager.flush();
			executed = true;
		}
	}
	
	@Test
	public void testFindAllUsers() {
		entityManager.persist(user);
		entityManager.flush();
		List<User> users = userRepository.findAll();
		assertThat(4, is(users.size())); //3 user dans le sql d'import + 1 dans la classe
	}
	
	@Test
	public void testSaveUser() {
		User user = new User("Paul", "password", 1);
		User userSaved = userRepository.save(user);
		Assertions.assertNotNull(userSaved.getId());
		assertThat("Paul", is(userSaved.getLogin()));
	}
	
	@Test
	public void testFindByLogin() {
		User userFromDB = userRepository.findByLogin("user1");
		assertThat("user1", is(userFromDB.getLogin()));
	}
	
	@Test
	public void testDeleteUser() {
		userRepository.deleteById(user.getId());
		User userFromDB = userRepository.findByLogin(user.getLogin());
		Assertions.assertNull(userFromDB);
	}
	
	@Test
	public void testUpdateUser() {
		User userToUpdate = userRepository.findByLogin(user.getLogin());
		userToUpdate.setActive(0);
		userRepository.save(userToUpdate);
		User userUpdatedFromDB = userRepository.findByLogin(userToUpdate.getLogin());
		Assertions.assertNotNull(userUpdatedFromDB);
		assertThat(0, is(userUpdatedFromDB.getActive()));
	}
	
}
