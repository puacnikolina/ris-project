package model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the song database table.
 * 
 */
@Entity
@NamedQuery(name="Song.findAll", query="SELECT s FROM Song s")
public class Song implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSong;

	private String duration;

	private String name;

	//bi-directional many-to-one association to Product
	@ManyToOne(fetch=FetchType.LAZY)
	private Product product;

	public Song() {
	}

	public int getIdSong() {
		return this.idSong;
	}

	public void setIdSong(int idSong) {
		this.idSong = idSong;
	}

	public String getDuration() {
		return this.duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}