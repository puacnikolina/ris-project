package model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the orders_has_product database table.
 * 
 */
@Entity
@Table(name="orders_has_product")
@NamedQuery(name="OrdersHasProduct.findAll", query="SELECT o FROM OrdersHasProduct o")
public class OrdersHasProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrdersHasProductPK id;

	private int quantity;

	//bi-directional many-to-one association to Order
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Orders_idOrder", insertable = false, updatable = false)
	private Order order;

	//bi-directional many-to-one association to Product
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Product_idProduct", insertable = false, updatable = false)
	private Product product;

	public OrdersHasProduct() {
	}

	public OrdersHasProductPK getId() {
		return this.id;
	}

	public void setId(OrdersHasProductPK id) {
		this.id = id;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}