/**
 * 
 */
package org.meruvian.yama.showcase.store.product;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author dianw
 *
 */
@Service
@Transactional(readOnly = true)
public class RestProductCategoryService implements ProductCategoryService {
	@Inject
	private ProductCategoryRepository categoryRepository;
	
	@Override
	public ProductCategory getCategoryById(String categoryId) {
		return categoryRepository.findById(categoryId);
	}

	@Override
	public Page<ProductCategory> findCategoryByKeyword(String keyword, Pageable pageable) {
		return categoryRepository.findByNameContainsOrDescriptionContains(keyword, keyword, pageable);
	}

	@Override
	@Transactional
	public ProductCategory saveCategory(ProductCategory category) {
		if (StringUtils.isBlank(category.getId())) {
			return categoryRepository.save(category);
		} else {
			ProductCategory c = categoryRepository.findById(category.getId());
			c.setName(category.getName());
			c.setDescription(category.getDescription());
			c.setParent(category.getParent());
			
			return c;
		}
	}

	@Override
	@Transactional
	public boolean removeCategory(String categoryId) {
		categoryRepository.delete(categoryId);
		
		return true;
	}

	@Override
	public Page<ProductCategory> findByParentIsNull(Pageable pageable) {
		return categoryRepository.findByParentIsNull(pageable);
	}

	@Override
	public Page<ProductCategory> findByParentIsNotNull(Pageable pageable) {
		return categoryRepository.findByParentIsNotNull(pageable);
	}

}
