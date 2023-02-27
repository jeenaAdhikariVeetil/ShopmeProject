package com.shopme.admin.category;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Category;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
public class CategoryRepositoryTest {

	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Test
	public void testRootCategory()
	{
		Category cat=new Category("Electronics");
		Category savedCat=categoryRepository.save(cat);
		assertThat(savedCat.getId()).isGreaterThan(0);
		
	}
	
	@Test
	public void testSubCategory()
	{
		Category parent=new Category(10);
		Category desktop=new Category("Desktops",parent);
		Category laptops=new Category("laptops",parent);
		Category computerComponents=new Category("computerComponents",parent);
		Category components=new Category("components",parent);
		Category savedCat=categoryRepository.save(desktop);
	Category savedCat1=categoryRepository.save(computerComponents);
	categoryRepository.saveAll(List.of(laptops,components));
		Category parent2=new Category(16);
		Category memory1=new Category("memory RAM",parent2);
		categoryRepository.save(memory1);
		assertThat(savedCat.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testCategory()
	{
		Category category=categoryRepository.findById(10).get();
		System.out.println(category.getName());
		Set<Category> subCategories=category.getChildren();
		for(Category cat:subCategories)
		{
			System.out.println(cat.getName());
		}
		assertThat(subCategories.size()).isGreaterThan(0);
	}
}
