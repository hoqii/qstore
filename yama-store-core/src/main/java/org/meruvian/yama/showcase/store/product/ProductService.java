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
public interface ProductService {
	Product getProductById(String id);
	
	Page<Product> findProductByKeyword(String keyword, Pageable pageable);
	
	Page<Product> findProductByCategoryAndKeyword(String categoryId, String keyword, Pageable pageable);
	
	Product saveProduct(Product product);
	
	boolean removeProduct(String productId);
}
