package be.promsoc.arlon.hibernate.maven;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product_table")
public class Product {

	@Id
	@Column(name = "product_id")
	private int productid;

	@Column(name = "product_name")
	private String productname;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	public Product(int productid, String productname, String createdBy) {
		super();
		this.productid = productid;
		this.productname = productname;
		this.createdBy = createdBy;
		createdDate = new Date();
	}

	public Product() {
		// TODO Auto-generated constructor stub
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
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

	@ManyToOne
	// Make sure that the column exists !!!
	@JoinColumn(name = "pack_id")
	private Pack pack;

	public Pack getPack() {
		return pack;
	}

	public void setPack(Pack pack) {
		this.pack = pack;

	}

}
