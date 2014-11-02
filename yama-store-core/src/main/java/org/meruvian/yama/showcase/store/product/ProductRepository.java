/**
 * 
 */
package org.meruvian.yama.showcase.store.product;

import org.meruvian.yama.core.DefaultRepository;
import org.meruvian.yama.core.LogInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author dianw
 *
 */
@Repository
public interface ProductRepository extends DefaultRepository<Product> {
	@Query("SELECT p FROM Product p WHERE p.logInformation.activeFlag = ?3 AND (p.name LIKE %?1% OR p.description LIKE %?2%)")
	Page<Product> findByNameContainsOrDescriptionContainsAndLogInformationActiveFlag(String name, String description, int active, Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.category.name Like %?4% AND  p.logInformation.activeFlag = ?3 AND (p.name LIKE %?1% OR p.description LIKE %?2%)")
	Page<Product> findByCategoryIdAndNameOrDescriptionContains(String name, String description, int active, String categoryId, Pageable pageable);
}
