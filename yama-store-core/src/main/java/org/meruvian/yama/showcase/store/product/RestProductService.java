/**
 * 
 */
package org.meruvian.yama.showcase.store.product;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.meruvian.yama.core.LogInformation;
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
public class RestProductService implements ProductService {
	@Inject
	private ProductRepository productRepository;
	
	@Override
	public Product getProductById(String productId) {
		return productRepository.findById(productId);
	}

	@Override
	@Transactional
	public Product saveProduct(Product product) {
		if (StringUtils.isBlank(product.getId())) {
			return productRepository.save(product);
		} else {
			Product c = productRepository.findById(product.getId());
			c.setName(product.getName());
			c.setDescription(product.getDescription());
			c.setCategory(product.getCategory());
			c.setUnitOfMeasure(product.getUnitOfMeasure());
			c.setPrice(product.getPrice());
			
			return c;
		}
	}

	@Override
	@Transactional
	public boolean removeProduct(String productId) {
		
		Product c = productRepository.findById(productId);
		c.getLogInformation().setActiveFlag(0);
		
		
		return true;
	}

	@Override
	public Page<Product> findProductByCategoryAndKeyword(String categoryId,
			String keyword, Pageable pageable) {
		return productRepository.findByCategoryIdAndNameOrDescriptionContains(keyword, keyword,LogInformation.ACTIVE, categoryId, pageable);
	}

	@Override
	public Page<Product> findProductByKeyword(String keyword, Pageable pageable) {
		return productRepository.findByNameContainsOrDescriptionContainsAndLogInformationActiveFlag(keyword, keyword,LogInformation.ACTIVE, pageable);
	}

}
