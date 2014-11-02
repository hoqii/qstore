/**
 * 
 */
package org.meruvian.yama.showcase.store.action;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.ActionParam;
import org.meruvian.yama.showcase.store.product.ProductCategory;
import org.meruvian.yama.showcase.store.product.ProductCategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author dianw
 *
 */
@Action(name = "/backend/product_category")
public class ProductCategoryAction extends ActionSupport {
	@Inject
	private ProductCategoryService categoryService;
	
	@Action(method = HttpMethod.GET)
	public ActionResult productCategoryList(@ActionParam("q") String q, @ActionParam("max") int max,
			@ActionParam("page") int page) {
		max = max == 0 ? 10 : max;
		q = q == null ? "" : q;
		Page<? extends ProductCategory> productCategories = categoryService.findCategoryByKeyword(q, new PageRequest(page, max));
		
		return new ActionResult("freemarker","/view/store/product_category/category-list.ftl")
				.addToModel("productCategories", productCategories);
	}
	
	@Action(name = "/{id}/edit", method = HttpMethod.GET)
	public ActionResult productCategoryForm(@ActionParam("id") String id) {
		ActionResult actionResult = new ActionResult("freemarker", "/view/store/product_category/category-form.ftl");
		
		if (!StringUtils.equalsIgnoreCase(id, "-")) {
			ProductCategory productCategory = categoryService.getCategoryById(id);
			actionResult.addToModel("category", productCategory);
		} else {
			actionResult.addToModel("category", new ProductCategory());
		}
		Page<? extends ProductCategory> parentCategories = categoryService.findByParentIsNull(null);
		actionResult.addToModel("parent", parentCategories);
		
		
		return actionResult;
	}
	
	@Action(name = "/{id}/delete", method = HttpMethod.GET)
	public ActionResult deleteProductCategory(@ActionParam("id") String id) {
		
		categoryService.removeCategory(id);
		
		String redirectUri = "/backend/product_category?success";
		
		return new ActionResult("redirect", redirectUri);
	}
	
	@Action(name = "/{id}/edit", method = HttpMethod.POST)
	public ActionResult updateProductCategory(@ActionParam("id") String id,  @ActionParam("category") ProductCategory productCategory
			,  @ActionParam("parent") String parent) {
		validateProductCategory(productCategory, id);
		if (hasFieldErrors()) {
			Page<? extends ProductCategory> parentCategories = categoryService.findByParentIsNull(null);
			return new ActionResult("freemarker", "/view/store/product_category/category-form.ftl").addToModel("parent", parentCategories);
		}

		ProductCategory parentCategory = categoryService.getCategoryById(parent);
		productCategory.setParent(parentCategory);
		ProductCategory r = categoryService.saveCategory(productCategory);
		String redirectUri = "/backend/product_category?success";
		
		return new ActionResult("redirect", redirectUri);
	}
	
	
	
	private void validateProductCategory(ProductCategory productCategory, String productCategoryname) {
		if (StringUtils.isBlank(productCategory.getName())) {
			addFieldError("category.name", getText("message.store.category.name.notempty"));
		} else if (!StringUtils.equalsIgnoreCase(productCategory.getId(), productCategoryname)) {
			if (categoryService.getCategoryById(productCategory.getId()) != null) {
				addFieldError("category.name", getText("message.store.category.name.exist"));
			}
		}
	}
}
