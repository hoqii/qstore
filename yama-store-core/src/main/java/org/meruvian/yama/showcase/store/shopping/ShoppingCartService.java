package org.meruvian.yama.showcase.store.shopping;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ShoppingCartService {
	
	ShoppingCart getShoppingCartById(String id);

	ShoppingCart getShoppingCartByIdentify(String cookiesId, int isPayed, String productId, String imageId);
	
	Page<ShoppingCart> findByuserAndIsPayed(String cookiesId, String createBy, int isPayed, Pageable pageable);

	ShoppingCart saveShopingChart(ShoppingCart chart);

	boolean removeShopingChart(String chartId);
}
