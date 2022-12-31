package com.shopme.admin.user;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	public List<User> listUsers()
	{
		return (List<User>) userRepo.findAll();
	}
	
	public List<Role> listRoles()
	{
		return (List<Role>) roleRepo.findAll();
	}
	
	public User SaveUser(User user)
	{
		return userRepo.save(user);
	}
	public boolean isEmailUnique(String email,Integer id)
	{
		
		User userByEmail=userRepo.getUserByEmail(email);
		if(userByEmail==null) return true;
	
		boolean isCreatingNew= (id==null);
		
		if(isCreatingNew)
		{
			if(userByEmail!=null) 
				
		return false;
		}
		return true;
	}

	public User get(int userId) throws UserNotFoundException {
		try
		{
			return userRepo.findById(userId).get();
		}
		catch(NoSuchElementException ex)
		{
			throw new UserNotFoundException("Could not find any user with ID "+userId);
		}
		
	}
	
	public void delete(Integer id) throws UserNotFoundException
	{
		Long countById=userRepo.countById(id);
		if(countById==null||countById==0)
		{
			throw new UserNotFoundException("Could not find any user with ID "+id);
		}
		userRepo.deleteById(id);
	}
	
}
