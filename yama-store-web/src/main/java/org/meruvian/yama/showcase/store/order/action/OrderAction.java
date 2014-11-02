/**
 * 
 */
package org.meruvian.yama.showcase.store.order.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.ActionParam;
import org.meruvian.inca.struts2.rest.annotation.Param;
import org.meruvian.inca.struts2.rest.annotation.Result;
import org.meruvian.inca.struts2.rest.annotation.Results;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.yama.core.commons.DefaultFileInfo;
import org.meruvian.yama.core.commons.FileInfo;
import org.meruvian.yama.core.commons.FileInfoManager;
import org.meruvian.yama.core.commons.JpaFileInfo;
import org.meruvian.yama.core.user.User;
import org.meruvian.yama.showcase.store.action.ShoppingCartCookieInterceptor;
import org.meruvian.yama.showcase.store.product.Product;
import org.meruvian.yama.showcase.store.product.Product;
import org.meruvian.yama.showcase.store.product.ProductCategory;
import org.meruvian.yama.showcase.store.product.ProductCategoryService;
import org.meruvian.yama.showcase.store.product.ProductImageService;
import org.meruvian.yama.showcase.store.product.ProductService;
import org.meruvian.yama.showcase.store.product.ProductService;
import org.meruvian.yama.showcase.store.product.UnitOfMeasure;
import org.meruvian.yama.showcase.store.product.UnitOfMeasureService;
import org.meruvian.yama.showcase.store.shopping.ShoppingCart;
import org.meruvian.yama.showcase.store.shopping.ShoppingCartService;
import org.meruvian.yama.web.SessionCredentials;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author dianw
 *
 */
@Action(name = "/order")
public class OrderAction extends ActionSupport {
	@Inject
	private ProductService productService;
	
	@Inject
	private ProductCategoryService categoryService;
	
	@Inject
	private UnitOfMeasureService uomService;
	
	@Inject
	private FileInfoManager fileInfoManager;
	
	@Inject
	private ProductImageService imageService;
	
	@Inject
	private ShoppingCartService shoppingCartService;
	
	private InputStream picture;

	public InputStream getPicture() {
		return picture;
	}
	
	@Action(name = "s1", method = HttpMethod.POST)
	public ActionResult productList(@ActionParam(ShoppingCartCookieInterceptor.CART_ID) String cartId, @ActionParam("cid") List<String> cid) {
		String userId = "";
		if(SessionCredentials.getCurrentUser() != null)
			userId = SessionCredentials.getCurrentUser().getId();
		Page<ShoppingCart> carts = shoppingCartService.findByuserAndIsPayed(cartId, userId, 0, null);
		Page<? extends ProductCategory> productCategories = categoryService.findByParentIsNotNull(null);
		
		List<ShoppingCart> order = new ArrayList<ShoppingCart>();
		
		for (String i : cid) {
			ShoppingCart tempCart = shoppingCartService.getShoppingCartById(i);
			order.add(tempCart);
		}
		
		return new ActionResult("freemarker","/view/store/order/order-s1.ftl").addToModel("categories", productCategories).addToModel("carts", carts).addToModel("orders", order);
	}
	
}
