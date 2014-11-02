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
public interface ProductImageRepository extends DefaultRepository<ProductImage> {
	Page<ProductImage> findByProductId(String productId, Pageable pageable);
}
