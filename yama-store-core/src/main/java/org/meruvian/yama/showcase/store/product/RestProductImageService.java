/**
 * 
 */
package org.meruvian.yama.showcase.store.product;

import java.io.InputStream;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.meruvian.yama.core.commons.JpaFileInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author dianw
 *
 */
@Service
@Transactional(readOnly = true)
public class RestProductImageService implements ProductImageService {
	@Inject
	private ProductImageRepository imageRepository;

	@Override
	public Page<ProductImage> findImageByProductId(String productId) {
		return imageRepository.findByProductId(productId, null);
	}

	@Override
	public InputStream getImageById(String productImageId) {
		return imageRepository.findById(productImageId).getImage().getDataBlob();
	}

	@Override
	public ProductImage saveImage(String productId, JpaFileInfo fileInfo) {
		ProductImage productImage = new ProductImage();
		Product product = new Product();
		product.setId(productId);
		productImage.setProduct(product);
		productImage.setImage(fileInfo);
		return imageRepository.save(productImage);
	}

	@Override
	public boolean deleteImage(String productImageId) {
		imageRepository.delete(productImageId);
		
		return true;
	}

	@Override
	public ProductImage findOne(String productImageId) {
		return imageRepository.findById(productImageId);
	}
	
	

}
