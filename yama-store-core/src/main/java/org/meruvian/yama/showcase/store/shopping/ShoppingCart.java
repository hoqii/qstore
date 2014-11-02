/**
 * 
 */
package org.meruvian.yama.showcase.store.shopping;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.meruvian.yama.core.DefaultJpaPersistence;
import org.meruvian.yama.core.LogInformation;
import org.meruvian.yama.showcase.store.product.Product;
import org.meruvian.yama.showcase.store.product.ProductImage;

@Entity
@Table(name = "yama_showcase_store_shopping_cart")
public class ShoppingCart extends DefaultJpaPersistence {
	private String cookiesId;
	private double price = 0;
	private int quantity = 0;
	private Product product;
	private ProductImage image;
	private int isPayed = 0;

	@Column(name = "cookiesId")
	public String getCookiesId() {
		return cookiesId;
	}

	public void setCookiesId(String cookiesId) {
		this.cookiesId = cookiesId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@ManyToOne
	@JoinColumn(name = "product")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@ManyToOne
	@JoinColumn(name = "image")
	public ProductImage getImage() {
		return image;
	}

	public void setImage(ProductImage image) {
		this.image = image;
	}

	public int getIsPayed() {
		return isPayed;
	}

	public void setIsPayed(int isPayed) {
		this.isPayed = isPayed;
	}

}
