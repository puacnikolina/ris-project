package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the artist database table.
 * 
 */
@Entity
@NamedQuery(name="Artist.findAll", query="SELECT a FROM Artist a")
public class Artist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idArtist;

	private String name;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy = "artist", cascade = CascadeType.REMOVE)
	private List<Product> products;

	public Artist() {
	}

	public int getIdArtist() {
		return this.idArtist;
	}

	public void setIdArtist(int idArtist) {
		this.idArtist = idArtist;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setArtist(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setArtist(null);

		return product;
	}

}