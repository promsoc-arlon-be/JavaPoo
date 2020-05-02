package be.promsoc.arlon.hibernate.maven;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pack_table")
public class Pack {

	@Id
	@Column(name = "pack_id")
	private int packid;

	@Column(name = "pack_name")
	private String packname;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	public Pack(int packid, String packname, String createdBy) {
		super();
		this.packid = packid;
		this.packname = packname;
		this.createdBy = createdBy;
		createdDate = new Date();
	}

	public Pack() {
		// TODO Auto-generated constructor stub
	}

	public int getPackid() {
		return packid;
	}

	public void setPackid(int packid) {
		this.packid = packid;
	}

	public String getPackname() {
		return packname;
	}

	public void setPackname(String packname) {
		this.packname = packname;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@ManyToOne
	// Make sure that the column exists !!!
	@JoinColumn(name = "user_id")
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/////
	@OneToMany(mappedBy = "pack")
	private Set<Product> products = new HashSet<Product>();

	/////
	public void addProduct(Product product) {
		this.products.add(product);
		product.setPack(this);
	}


}
