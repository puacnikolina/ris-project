package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idProduct;

	private String description;

	@Lob
	@Column(name = "img", columnDefinition = "LONGBLOB")
	private byte[] img;

	private String name;

	private int price;

	private int quantity;

	private String rating;

	@Temporal(TemporalType.TIMESTAMP)
	private Date releaseDate;

	//bi-directional many-to-one association to OrdersHasProduct
	@OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
	private List<OrdersHasProduct> ordersHasProducts;

	//bi-directional many-to-one association to Artist
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Artist_idArtist")  
	private Artist artist;
	
	//bi-directional many-to-one association to Category
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Category_idCategory") 
	private Category category;

	//bi-directional many-to-one association to Song
    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
	private List<Song> songs;

	//bi-directional many-to-many association to Wishlist
	@ManyToMany(mappedBy="products")
	private List<Wishlist> wishlists;

	public Product() {
	}

	public int getIdProduct() {
		return this.idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getImg() {
		return this.img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getRating() {
		return this.rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Date getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public List<OrdersHasProduct> getOrdersHasProducts() {
		return this.ordersHasProducts;
	}

	public void setOrdersHasProducts(List<OrdersHasProduct> ordersHasProducts) {
		this.ordersHasProducts = ordersHasProducts;
	}

	public OrdersHasProduct addOrdersHasProduct(OrdersHasProduct ordersHasProduct) {
		getOrdersHasProducts().add(ordersHasProduct);
		ordersHasProduct.setProduct(this);

		return ordersHasProduct;
	}

	public OrdersHasProduct removeOrdersHasProduct(OrdersHasProduct ordersHasProduct) {
		getOrdersHasProducts().remove(ordersHasProduct);
		ordersHasProduct.setProduct(null);

		return ordersHasProduct;
	}

	public Artist getArtist() {
		return this.artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Song> getSongs() {
		return this.songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public Song addSong(Song song) {
		getSongs().add(song);
		song.setProduct(this);

		return song;
	}

	public Song removeSong(Song song) {
		getSongs().remove(song);
		song.setProduct(null);

		return song;
	}

	public List<Wishlist> getWishlists() {
		return this.wishlists;
	}

	public void setWishlists(List<Wishlist> wishlists) {
		this.wishlists = wishlists;
	}

}