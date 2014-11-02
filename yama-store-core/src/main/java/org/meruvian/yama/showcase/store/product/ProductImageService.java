/**
 * 
 */
package org.meruvian.yama.showcase.store.product;

import java.io.InputStream;

import org.meruvian.yama.core.commons.JpaFileInfo;
import org.springframework.data.domain.Page;

/**
 * @author dianw
 *
 */
public interface ProductImageService {
	Page<ProductImage> findImageByProductId(String productId);
	
	InputStream getImageById(String productImageId);
	
	ProductImage findOne(String productImageId);
	
	ProductImage saveImage(String productId, JpaFileInfo fileInfo);
	
	boolean deleteImage(String productImageId);
}
