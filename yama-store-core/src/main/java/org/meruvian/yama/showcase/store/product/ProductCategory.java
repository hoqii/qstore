/**
 * 
 */
package org.meruvian.yama.showcase.store.product;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.meruvian.yama.core.DefaultJpaPersistence;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author dianw
 * 
 */
@Entity
@Table(name = "yama_showcase_store_product_category")
public class ProductCategory extends DefaultJpaPersistence {
	private String name;
	private String description;
	private ProductCategory parent;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne
	@JoinColumn(name = "parent_id")
	public ProductCategory getParent() {
		return parent;
	}

	public void setParent(ProductCategory parent) {
		this.parent = parent;
	}

}
