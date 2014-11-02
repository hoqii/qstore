/**
 * 
 */
package org.meruvian.yama.showcase.store.shopping;

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
public class RestShoppingCartService implements ShoppingCartService {
	@Inject
	private ShoppingCartRepository cartRepository;
	
	@Override
	public ShoppingCart getShoppingCartById(String cartId) {
		return cartRepository.findById(cartId);
	}

	@Override
	@Transactional
	public ShoppingCart saveShopingChart(ShoppingCart cart) {
		if (StringUtils.isBlank(cart.getId())) {
			return cartRepository.save(cart);
		} else {
			ShoppingCart c = cartRepository.findById(cart.getId());
			c.setQuantity(cart.getQuantity());
			c.setPrice(cart.getPrice());
			c.setImage(cart.getImage());
			c.setIsPayed(cart.getIsPayed());
			c.setProduct(cart.getProduct());;
			c.setCookiesId(cart.getCookiesId());
			return c;
		}
	}

	@Override
	@Transactional
	public boolean removeShopingChart(String cartId) {
		
		cartRepository.delete(cartId);
		
		
		return true;
	}

	@Override
	public Page<ShoppingCart> findByuserAndIsPayed(String cookiesId,
			String createBy, int isPayed, Pageable pageable) {
		return cartRepository.findByCookiesIdOrLogInformationCreateByAndIsPayed(cookiesId, createBy, isPayed, pageable);
	}

	@Override
	public ShoppingCart getShoppingCartByIdentify(String cookiesId, int isPayed, String productId, String imageId) {
		return cartRepository.findByCookiesIdAndIsPayedAndProductIdAndImageId(cookiesId, isPayed, productId, imageId);
	}

	
	

}
