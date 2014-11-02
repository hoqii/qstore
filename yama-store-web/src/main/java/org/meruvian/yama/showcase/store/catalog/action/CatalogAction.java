/**
 * 
 */
package org.meruvian.yama.showcase.store.catalog.action;

import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.ActionParam;
import org.meruvian.inca.struts2.rest.annotation.Param;
import org.meruvian.inca.struts2.rest.annotation.Result;
import org.meruvian.inca.struts2.rest.annotation.Results;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.yama.core.commons.FileInfo;
import org.meruvian.yama.core.commons.FileInfoManager;
import org.meruvian.yama.showcase.store.action.ShoppingCartCookieInterceptor;
import org.meruvian.yama.showcase.store.product.Product;
import org.meruvian.yama.showcase.store.product.ProductCategory;
import org.meruvian.yama.showcase.store.product.ProductCategoryService;
import org.meruvian.yama.showcase.store.product.ProductService;
import org.meruvian.yama.showcase.store.shopping.ShoppingCart;
import org.meruvian.yama.showcase.store.shopping.ShoppingCartService;
import org.meruvian.yama.web.SessionCredentials;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


@Action(name = "/")
public class CatalogAction {
	
	@Inject
	private ShoppingCartService shoppingCartService;
	
	@Inject
	private ProductService productService;
	
	@Inject
	private ProductCategoryService categoryService;
	
	@Inject
	private FileInfoManager fileInfoManager;
	
	private InputStream picture;

	public InputStream getPicture() {
		return picture;
	}
	
	@Action
	public ActionResult index(@ActionParam("q") String q, @ActionParam("c") String c, @ActionParam("max") int max,
			@ActionParam("page") int page, @ActionParam(ShoppingCartCookieInterceptor.CART_ID) String cartId) {
		max = max == 0 ? 10 : max;
		q = q == null ? "" : q;
		c = c == null ? "" : c;
		
		String userId = "";
		if(SessionCredentials.getCurrentUser() != null)
			userId = SessionCredentials.getCurrentUser().getId();
		
		Page<ShoppingCart> carts = shoppingCartService.findByuserAndIsPayed(cartId, userId, 0, new PageRequest(page, max));
		Page<Product> products = productService.findProductByCategoryAndKeyword(c, q, new PageRequest(page, max));
		Page<? extends ProductCategory> productCategories = categoryService.findByParentIsNotNull(null);
		
		return new ActionResult("freemarker", "/view/store/catalog/catalog.ftl").addToModel("q", q).addToModel("c", c)
				.addToModel("carts", carts).addToModel("products", products).addToModel("categories", productCategories);
	}
	
	@Action(name= "/p/{id}/detail")
	public ActionResult detail(@ActionParam("id") String id,@ActionParam("q") String q, @ActionParam("c") String c, @ActionParam("max") int max,
			@ActionParam("page") int page, @ActionParam(ShoppingCartCookieInterceptor.CART_ID) String cartId,@ActionParam("modal") int modal) {
		max = max == 0 ? 10 : max;
		q = q == null ? "" : q;
		c = c == null ? "" : c;
		String userId = "";
		
		if(SessionCredentials.getCurrentUser() != null)
			userId = SessionCredentials.getCurrentUser().getId();
		
		Page<ShoppingCart> carts = shoppingCartService.findByuserAndIsPayed(cartId, userId, 0, new PageRequest(page, max));
		Page<? extends ProductCategory> productCategories = categoryService.findByParentIsNotNull(null);
		Product product = productService.getProductById(id);
		Page<Product> products = productService.findProductByCategoryAndKeyword(c, q, new PageRequest(page, max));
		return new ActionResult("freemarker", "/view/store/catalog/detail.ftl").addToModel("carts", carts).addToModel("id", id).addToModel("categories", productCategories).addToModel("product", product).addToModel("products", products).addToModel("modal", modal);
	}
	
	@Action(name = "/i/{id}/image", method = HttpMethod.GET)
	@Results({
		@Result(name = "no-profile", type = "redirect", location = "/images/no-profile.jpg"),
		@Result(name = "image", type = "stream", 
			params = { 
				@Param(name = "contentType", value = "image/jpg"),
				@Param(name = "inputName", value = "picture")
			})
	})
	
	public String imageProduct(@ActionParam("id") String id) throws IOException {
		FileInfo fileInfo = fileInfoManager.getFileInfoById(id);
		if (fileInfo == null) {
			return "no-profile";
		}
		
		fileInfo = fileInfoManager.getFileInfoById(fileInfo.getId());
		picture = fileInfo.getDataBlob();
		
		return "image";
	}
}