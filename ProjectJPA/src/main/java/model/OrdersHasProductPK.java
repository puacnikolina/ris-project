package model;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The primary key class for the orders_has_product database table.
 * 
 */
@Embeddable
public class OrdersHasProductPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "Orders_idOrder")
	private int orders_idOrder;

	@Column(name = "Product_idProduct")
	private int product_idProduct;

	public OrdersHasProductPK() {
	}
	public int getOrders_idOrder() {
		return this.orders_idOrder;
	}
	public void setOrders_idOrder(int orders_idOrder) {
		this.orders_idOrder = orders_idOrder;
	}
	public int getProduct_idProduct() {
		return this.product_idProduct;
	}
	public void setProduct_idProduct(int product_idProduct) {
		this.product_idProduct = product_idProduct;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OrdersHasProductPK)) {
			return false;
		}
		OrdersHasProductPK castOther = (OrdersHasProductPK)other;
		return 
			(this.orders_idOrder == castOther.orders_idOrder)
			&& (this.product_idProduct == castOther.product_idProduct);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.orders_idOrder;
		hash = hash * prime + this.product_idProduct;
		
		return hash;
	}
}