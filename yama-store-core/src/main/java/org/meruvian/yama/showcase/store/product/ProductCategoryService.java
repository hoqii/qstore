/**
 * 
 */
package org.meruvian.yama.showcase.store.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author dianw
 *
 */
public interface ProductCategoryService {
	ProductCategory getCategoryById(String categoryId);
	
	Page<ProductCategory> findCategoryByKeyword(String keyword, Pageable pageable);
	
	Page<ProductCategory> findByParentIsNull(Pageable pageable);
	
	Page<ProductCategory> findByParentIsNotNull(Pageable pageable);
	
	ProductCategory saveCategory(ProductCategory category);
	
	boolean removeCategory(String categoryId);
}
