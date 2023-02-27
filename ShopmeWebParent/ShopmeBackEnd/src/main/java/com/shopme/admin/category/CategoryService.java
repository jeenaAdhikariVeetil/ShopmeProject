/**
 * 
 */
package com.shopme.admin.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopme.common.entity.Category;

/**
 * @author Jeena A V
 *
 */
@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	public List<Category> getCategories()
	{
		return categoryRepository.findAll();
	}

}
