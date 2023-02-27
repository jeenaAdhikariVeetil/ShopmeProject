/**
 * 
 */
package com.shopme.admin.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shopme.common.entity.Category;

/**
 * @author Jeena A V
 *
 */
@Controller
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/categories")
	public String listAll(Model model)
	{
		List<Category> listCategories=categoryService.getCategories();
		model.addAttribute("listCategories",listCategories);
		System.out.println("list users");
		return "categories/categories";
	}
	
	@GetMapping("/categories/new")
	public String newCatgeory(Model model)
	{
		model.addAttribute("category",new Category());
		model.addAttribute("pageTitle","Create New Category");
		return "categories/categories_form";
	}
}
