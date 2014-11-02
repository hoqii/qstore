/**
 * 
 */
package org.meruvian.yama.showcase.store.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

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
import org.meruvian.yama.showcase.store.product.Product;
import org.meruvian.yama.showcase.store.product.Product;
import org.meruvian.yama.showcase.store.product.ProductCategory;
import org.meruvian.yama.showcase.store.product.ProductCategoryService;
import org.meruvian.yama.showcase.store.product.ProductImageService;
import org.meruvian.yama.showcase.store.product.ProductService;
import org.meruvian.yama.showcase.store.product.ProductService;
import org.meruvian.yama.showcase.store.product.UnitOfMeasure;
import org.meruvian.yama.showcase.store.product.UnitOfMeasureService;
import org.meruvian.yama.web.SessionCredentials;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author dianw
 *
 */
@Action(name = "/backend/product")
public class ProductAction extends ActionSupport {
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
	
	private InputStream picture;

	public InputStream getPicture() {
		return picture;
	}
	
	@Action(method = HttpMethod.GET)
	public ActionResult productList(@ActionParam("q") String q, @ActionParam("max") int max,
			@ActionParam("page") int page) {
		max = max == 0 ? 10 : max;
		q = q == null ? "" : q;
		Page<Product> products = productService.findProductByKeyword(q, new PageRequest(page, max));
		
		return new ActionResult("freemarker","/view/store/product/product-list.ftl")
				.addToModel("products", products);
	}
	
	@Action(name = "/{id}/edit", method = HttpMethod.GET)
	public ActionResult productForm(@ActionParam("id") String id) {
		ActionResult actionResult = new ActionResult("freemarker", "/view/store/product/product-form.ftl");
		
		if (!StringUtils.equalsIgnoreCase(id, "-")) {
			Product product = productService.getProductById(id);
			actionResult.addToModel("product", product);
		} else {
			actionResult.addToModel("product", new Product());
		}
		
		actionResult.addToModel("uoms", uomService.findUnitOfMeasureByKeyword("", null));
		actionResult.addToModel("categories", categoryService.findByParentIsNotNull(null));
		
		return actionResult;
	}
	
	@Action(name = "/{id}/delete", method = HttpMethod.GET)
	public ActionResult deleteProduct(@ActionParam("id") String id) {
		
		productService.removeProduct(id);
		
		String redirectUri = "/backend/product?success";
		
		return new ActionResult("redirect", redirectUri);
	}
	
	@Action(name = "/{id}/image", method = HttpMethod.GET)
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
	
	@Action(name = "/{id}/edit", method = HttpMethod.POST)
	public ActionResult updateProduct(@ActionParam("id") String id,  @ActionParam("product") Product product
			, @ActionParam("category") String category, @ActionParam("uom") String uom, @ActionParam("productImage") File file,
			@ActionParam("productImageFileName") String fileName) throws IOException {
		ActionResult actionResult = new ActionResult("freemarker", "/view/store/product/product-form.ftl");
		
		validateProduct(actionResult, product, id, uom, category, file);
		
		if (hasFieldErrors()) {
			ProductCategory productCategory = new ProductCategory();
			productCategory.setId(category);
			product.setCategory(productCategory);
			
			UnitOfMeasure productUom = new UnitOfMeasure();
			productUom.setId(uom);
			product.setUnitOfMeasure(productUom);
			
			actionResult.addToModel("product", product);
			actionResult.addToModel("uoms", uomService.findUnitOfMeasureByKeyword("", null));
			actionResult.addToModel("categories", categoryService.findByParentIsNotNull(null));
			return actionResult;
		}

		
		ProductCategory productCategory = new ProductCategory();
		productCategory.setId(category);
		product.setCategory(productCategory);
		
		UnitOfMeasure productUom = new UnitOfMeasure();
		productUom.setId(uom);
		product.setUnitOfMeasure(productUom);
		
		Product p = productService.saveProduct(product);
		try {
			JpaFileInfo fileInfo = new JpaFileInfo();
			fileInfo.setOriginalName(fileName);
			fileInfo.setDataBlob(new FileInputStream(file));
			fileInfo.setPath("");
			
			FileInfo f = fileInfoManager.saveFileInfo(fileInfo);
			
			imageService.saveImage(p.getId(), new JpaFileInfo(f));
		} catch (Exception e) {
			
		}
		
		
		String redirectUri = "/backend/product?success";
		
		return new ActionResult("redirect", redirectUri);
	}
	
	
	
	private void validateProduct(ActionResult actionResult, Product product, String productname, String uom, String category, File fileName) {
		if (StringUtils.isBlank(product.getName())) {
			addFieldError("product.name", getText("message.store.product.name.notempty"));
		} else if (!StringUtils.equalsIgnoreCase(product.getId(), productname)) {
			if (productService.getProductById(product.getId()) != null) {
				addFieldError("product.name", getText("message.store.product.name.exist"));
			}
		}
		
		if (StringUtils.isBlank(uom)) {
			actionResult.addToModel("uomEmty", getText("message.store.uom.notempty"));
			addFieldError("uom", getText("message.store.uom.notempty"));
		}
		
		if (StringUtils.isBlank(category)) {
			actionResult.addToModel("categoryEmty", getText("message.store.category.notempty"));
			addFieldError("category", getText("message.store.category.notempty"));
		}
		
		if (fileName == null) {
			actionResult.addToModel("imageEmpty", getText("message.store.image.notempty"));
			addFieldError("productImage", getText("message.store.image.notempty"));
		}
		
	}
}
