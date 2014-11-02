/**
 * 
 */
package org.meruvian.yama.showcase.store.product;

import org.meruvian.yama.core.DefaultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


@Repository
public interface UnitOfMeasureRepository extends DefaultRepository<UnitOfMeasure> {

	Page<UnitOfMeasure> findByNameContainsOrDescriptionContains(String name, String description, Pageable pageable);
	
}
