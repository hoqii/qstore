/**
 * 
 */
package org.meruvian.yama.showcase.store.product;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.meruvian.yama.core.DefaultJpaPersistence;

/**
 * @author dianw
 * 
 */

@Entity
@Table(name = "yama_showcase_store_product")
public class Product extends DefaultJpaPersistence {
	
	private String name;
	private String description;
	private double price = 0;
	private ProductCategory category;
	private UnitOfMeasure unitOfMeasure;
	private List<ProductImage> images = new ArrayList<ProductImage>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Column(length = 1000)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne
	@JoinColumn(name = "product_category_id")
	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	@ManyToOne
	@JoinColumn(name = "uom_id")
	public UnitOfMeasure getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	public List<ProductImage> getImages() {
		return images;
	}

	public void setImages(List<ProductImage> images) {
		this.images = images;
	}

}
