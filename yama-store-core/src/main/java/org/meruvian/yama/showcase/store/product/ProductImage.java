/**
 * 
 */
package org.meruvian.yama.showcase.store.product;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.meruvian.yama.core.DefaultJpaPersistence;
import org.meruvian.yama.core.commons.JpaFileInfo;

/**
 * @author dianw
 * 
 */
@Entity
@Table(name = "yama_showcase_store_product_image", uniqueConstraints = { @UniqueConstraint(columnNames = { "product_id", "file_info_id" }) })
public class ProductImage extends DefaultJpaPersistence {
	private Product product;
	private JpaFileInfo image;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "product_id")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@ManyToOne
	@JoinColumn(name = "file_info_id")
	public JpaFileInfo getImage() {
		return image;
	}

	public void setImage(JpaFileInfo image) {
		this.image = image;
	}
}
