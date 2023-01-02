package com.shopme.admin.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;



@Controller
public class UserController {

	
	@Autowired
	private UserService userservice;
	
	@GetMapping("/users")
	public String listAll(Model model)
	{
		List<User> listUsers=userservice.listUsers();
		model.addAttribute("listUsers",listUsers);
		System.out.println("list users");
		return "users";
	}
	
	@GetMapping("/users/new")
	public String newUser(Model model)
	{
		List<Role> listRoles=userservice.listRoles();
		User user=new User();
		user.setEnabled(true);
		model.addAttribute("user",user);
		model.addAttribute("listRoles",listRoles);
		model.addAttribute("pageTitle","Create New User");
		return "user_form";
	}
	
	@PostMapping("/users/save")
	public String saveUser(User user,RedirectAttributes redirectattributes)
	{
		System.out.println("user....");
		userservice.SaveUser(user);
		redirectattributes.addFlashAttribute("message","User has been saved successfully");
		return "redirect:/users";
		
	}
	
	@GetMapping("/users/edit/{userId}")
	public String edit(@PathVariable int userId,
			Model model,
			RedirectAttributes redirectattributes)
	{
		try {
			User user=userservice.get(userId);
			List<Role> listRoles=userservice.listRoles();
			model.addAttribute("user",user);
			model.addAttribute("listRoles",listRoles);
			model.addAttribute("pageTitle","Edit User (ID : "+ userId + " )");
			return "user_form";
		} catch (UserNotFoundException ex) {
			redirectattributes.addFlashAttribute("message",ex.getMessage());
			return "redirect:/users";
		}
	
		
	}
	
	@GetMapping("/users/delete/{userId}")
	public String deleteUser(@PathVariable int userId,
			Model model,
			RedirectAttributes redirectattributes)
	{
		try {
			userservice.delete(userId);
			redirectattributes.addFlashAttribute("message","The user ID "+userId+" has been deleted Successfully");
		} catch (UserNotFoundException ex) {
			redirectattributes.addFlashAttribute("message",ex.getMessage());
			
		}
		return "redirect:/users";
	}
	
	@GetMapping("/users/{id}/enabled/{status}")
	public String updateEnabledStatus(@PathVariable("id") Integer userId,
			@PathVariable("status") boolean enabled,RedirectAttributes redirectattributes)
	{
		userservice.updateEnabledStatus(userId, enabled);
		System.out.println("hello");
		String status=enabled?"enabled" :"disabled";
		String message="The user ID "+userId+ " has been "+status;
		redirectattributes.addFlashAttribute("message",message);
		return "redirect:/users";
		
	}
	
	
}
