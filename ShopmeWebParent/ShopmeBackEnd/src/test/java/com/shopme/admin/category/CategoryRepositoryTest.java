package com.shopme.admin.category;

import static org.assertj.core.api.Assertions.assertThat;

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
		Category parent=new Category(1);
		Category subCat=new Category("Desktops",parent);
		Category savedCat=categoryRepository.save(subCat);
		assertThat(savedCat.getId()).isGreaterThan(0);
	}
}
