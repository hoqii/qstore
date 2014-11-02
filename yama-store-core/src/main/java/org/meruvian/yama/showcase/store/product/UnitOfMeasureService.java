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
public interface UnitOfMeasureService {
	UnitOfMeasure getUnitOfMeasureById(String id);
	
	Page<UnitOfMeasure> findUnitOfMeasureByKeyword(String keyword, Pageable pageable);
	
	UnitOfMeasure saveUnitOfMeasure(UnitOfMeasure product);
	
	boolean removeUnitOfMeasure(String productId);
}
