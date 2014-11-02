package org.meruvian.yama.showcase.store.action;

import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.ActionParam;
import org.meruvian.yama.showcase.store.product.Product;
import org.meruvian.yama.showcase.store.product.ProductCategory;
import org.meruvian.yama.showcase.store.product.ProductCategoryService;
import org.meruvian.yama.showcase.store.product.ProductImage;
import org.meruvian.yama.showcase.store.product.ProductImageService;
import org.meruvian.yama.showcase.store.product.ProductService;
import org.meruvian.yama.showcase.store.shopping.ShoppingCart;
import org.meruvian.yama.showcase.store.shopping.ShoppingCartService;
import org.meruvian.yama.web.SessionCredentials;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.remoting.soap.SoapFaultException;

import com.opensymphony.xwork2.ActionSupport;

@Action(name = "/shopping/cart")
public class ShoppingCartAction extends ActionSupport {

	@Inject
	private ProductService productService;
	
	@Inject
	private ProductImageService imageService;
	
	@Inject
	private ShoppingCartService shoppingCartService;
	
	@Inject
	private ProductCategoryService categoryService;
	
	private InputStream picture;

	public InputStream getPicture() {
		return picture;
	}
	
	@Action
	public ActionResult productList(@ActionParam("q") String q, @ActionParam("max") int max,
			@ActionParam("page") int page, @ActionParam(ShoppingCartCookieInterceptor.CART_ID) String cartId) {
		max = max == 0 ? 10 : max;
		q = q == null ? "" : q;
		
		String userId = "";
		if(SessionCredentials.getCurrentUser() != null)
			userId = SessionCredentials.getCurrentUser().getId();
		
		Page<ShoppingCart> carts = shoppingCartService.findByuserAndIsPayed(cartId, userId, 0, new PageRequest(page, max));
		Page<? extends ProductCategory> productCategories = categoryService.findCategoryByKeyword(q, new PageRequest(page, max));
		
		return new ActionResult("freemarker","/view/store/catalog/cart-list.ftl")
				.addToModel("carts", carts).addToModel("categories", productCategories);
	}
		
	@Action(name = "/{id}/edit", method = HttpMethod.POST)
	public ActionResult updateProduct(@ActionParam("id") String id, @ActionParam("product") String productId, @ActionParam("shoping") ShoppingCart cart,@ActionParam("image") String imageId, @ActionParam(ShoppingCartCookieInterceptor.CART_ID) String cartId) {

		ShoppingCart shoppingCart = shoppingCartService.getShoppingCartByIdentify(cartId, 0, productId, imageId);
		if(shoppingCart != null)
			cart.setId(shoppingCart.getId());
		
		Product p = productService.getProductById(productId);
		ProductImage i = imageService.findOne(imageId);
		System.out.println(imageId+"|"+i);
		cart.setProduct(p);
		cart.setPrice(p.getPrice());
		cart.setImage(i);
		cart.setCookiesId(cartId);
		
		shoppingCartService.saveShopingChart(cart);
		
		String redirectUri = "/p/"+p.getId()+"/detail?modal=1&c="+p.getCategory().getName();
		
		return new ActionResult("redirect", redirectUri);
	}
	
	@Action(name = "/{id}/qty/update", method = HttpMethod.POST)
	public ActionResult updateqty(@ActionParam("id") String id, @ActionParam("qty") int qty) {

		ShoppingCart shoppingCart = shoppingCartService.getShoppingCartById(id);
		shoppingCart.setQuantity(qty);
		
		shoppingCartService.saveShopingChart(shoppingCart);
		
		String redirectUri = "/shopping/cart";
		
		return new ActionResult("redirect", redirectUri);
	}
	
	@Action(name = "/{id}/delete", method = HttpMethod.GET)
	public ActionResult deleteCart(@ActionParam("id") String id) {

		shoppingCartService.removeShopingChart(id);
		
		String redirectUri = "/shopping/cart";
		
		return new ActionResult("redirect", redirectUri);
	}
	
}
