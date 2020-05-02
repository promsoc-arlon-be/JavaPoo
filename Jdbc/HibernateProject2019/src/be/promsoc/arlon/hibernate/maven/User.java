package be.promsoc.arlon.hibernate.maven;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user_table")
public class User {

	@Id
	@Column(name = "user_id")
	private int userid;

	@Column(name = "user_name")
	private String username;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	public User(int userid, String username, String createdBy) {
		super();
		this.userid = userid;
		this.username = username;
		this.createdBy = createdBy;
		createdDate = new Date();
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	/////
	@OneToMany(mappedBy = "user")
	private Set<Product> products = new HashSet<Product>();

	public void addProduct(Product product) {
		this.products.add(product);
		product.setUser(this);
	}

	/////
	@OneToMany(mappedBy = "user")
	private Set<Pack> packs = new HashSet<Pack>();

	public void addPack(Pack pack) {
		this.packs.add(pack);
		pack.setUser(this);
	}
}
