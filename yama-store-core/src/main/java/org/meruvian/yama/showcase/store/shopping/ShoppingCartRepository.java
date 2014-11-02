/**
 * 
 */
package org.meruvian.yama.showcase.store.shopping;

import org.meruvian.yama.core.DefaultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * @author dianw
 *
 */
@Repository
public interface ShoppingCartRepository extends DefaultRepository<ShoppingCart> {
	
	ShoppingCart findByCookiesIdAndIsPayedAndProductIdAndImageId(String cookiesId, int isPayed, String productId, String imageId);
	
	Page<ShoppingCart> findByCookiesIdOrLogInformationCreateByAndIsPayed(String cookiesId, String createBy, int isPayed, Pageable pageable);

}
