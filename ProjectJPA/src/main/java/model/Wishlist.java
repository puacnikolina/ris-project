package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the wishlist database table.
 * 
 */
@Entity
@NamedQuery(name="Wishlist.findAll", query="SELECT w FROM Wishlist w")
public class Wishlist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idWishlist;

	//bi-directional many-to-many association to Product
	@ManyToMany
	@JoinTable(
		name="wishlist_has_product"
		, joinColumns={
			@JoinColumn(name="Wishlist_idWishlist")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Product_idProduct")
			}
		)
	private List<Product> products;

	//bi-directional one-to-one association to User
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "User_idUser", nullable = false)
	private User user;


	public Wishlist() {
	}

	public int getIdWishlist() {
		return this.idWishlist;
	}

	public void setIdWishlist(int idWishlist) {
		this.idWishlist = idWishlist;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}