/**
 * 
 */
package org.meruvian.yama.showcase.store.product;

import org.meruvian.yama.core.DefaultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * @author dianw
 *
 */
@Repository
public interface ProductCategoryRepository extends DefaultRepository<ProductCategory> {
	Page<ProductCategory> findByNameContainsOrDescriptionContains(String name, String description, Pageable pageable);
	
	Page<ProductCategory> findByParentIsNull(Pageable pageable);
	
	Page<ProductCategory> findByParentIsNotNull(Pageable pageable);
	
}
