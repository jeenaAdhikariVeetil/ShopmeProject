package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
	@Autowired
	private UserRepository repo;
	@Autowired
	TestEntityManager entityManager;

	// user with one role
	@Test
	public void testCreateNewUserWithOneRole() {
		Role roleAdmin = entityManager.find(Role.class, 1);
		User userJee = new User("jeenalohiii@gmail.com", "jee2020", "jeena", "lohi");
		userJee.addRole(roleAdmin);
		User savedUser = repo.save(userJee);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}

	// user with 2 roles
	@Test
	public void testCreateNewUserWithTwoRoles() {
		User userAru = new User("arunkravi@gmail.com", "aru2022", "Arun", "KR");
		Role roleEditor = new Role(3);
		Role roleAssistant = new Role(5);
		userAru.addRole(roleEditor);
		userAru.addRole(roleAssistant);
		User savedUser = repo.save(userAru);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}

	@Test
	public void listAllUsers() {
		Iterable<User> listUsers = repo.findAll();
		listUsers.forEach(user -> System.out.println(user));

	}

	@Test
	public void testGetUserById() {
		User userJee = repo.findById(1).get();
		System.out.println(userJee);
		assertThat(userJee).isNotNull();
	}

	@Test
	public void testUpdateUserDetails() {
		User userJee = repo.findById(1).get();
		userJee.setEnabled(true);
		userJee.setEmail("jeenami@gmail.com");
		repo.save(userJee);
	}
	@Test
	public void testUpdateUserRole()
	{
		User userAru = repo.findById(2).get();
		Role roleEditor = new Role(3);
		Role roleSalesPerson = new Role(2);
		userAru.getRoles().remove(roleEditor);
		userAru.addRole(roleSalesPerson);
		repo.save(userAru);
		
	}
	
	@Test
	public void testDeleteUser()
	{
		User userAru = repo.findById(2).get();
		repo.delete(userAru);
	}
	//test user by email
	
	@Test
	public void testUserByEmail()
	{
		String email="test123@gmail.com";
				//"abc@gmail.com";
		User user=repo.getUserByEmail(email);
		assertThat(user).isNotNull();
	}
	
	@Test
	public void testCountByID()
	{
		Integer id=1;
		Long countById=repo.countById(id);
		assertThat(countById).isNotNull().isGreaterThan(0);
	}

}
