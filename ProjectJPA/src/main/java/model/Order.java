package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the orders database table.
 * 
 */
@Entity
@Table(name="orders")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idOrder;

	@Temporal(TemporalType.TIMESTAMP)
	private Date deliveryDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;

	private String status;

	private int totalPrice;

	//bi-directional many-to-one association to OrdersHasProduct
	@OneToMany(mappedBy="order")
	private List<OrdersHasProduct> ordersHasProducts;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="User_idUser", referencedColumnName="idUser")
	private User user;

	public Order() {
	}

	public int getIdOrder() {
		return this.idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public Date getDeliveryDate() {
		return this.deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<OrdersHasProduct> getOrdersHasProducts() {
		return this.ordersHasProducts;
	}

	public void setOrdersHasProducts(List<OrdersHasProduct> ordersHasProducts) {
		this.ordersHasProducts = ordersHasProducts;
	}

	public OrdersHasProduct addOrdersHasProduct(OrdersHasProduct ordersHasProduct) {
		getOrdersHasProducts().add(ordersHasProduct);
		ordersHasProduct.setOrder(this);

		return ordersHasProduct;
	}

	public OrdersHasProduct removeOrdersHasProduct(OrdersHasProduct ordersHasProduct) {
		getOrdersHasProducts().remove(ordersHasProduct);
		ordersHasProduct.setOrder(null);

		return ordersHasProduct;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}