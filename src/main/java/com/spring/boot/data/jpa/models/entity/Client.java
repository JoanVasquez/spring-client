package com.spring.boot.data.jpa.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tbl_clients")
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "client_id")
	private Long id;

	@Column(name = "first_name", nullable = false)
	@NotEmpty
	private String fName;

	@Column(name = "last_name", nullable = false)
	@NotEmpty
	private String lName;

	@Column(nullable = false, unique = true)
	@NotEmpty
	@Email
	private String email;

	@OneToMany(mappedBy = "client",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Bill> bills;

	@Column(name = "created_at", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private Date createdAt;

	public Client() {
		bills = new LinkedList<>();
	}

	public Client(Long id, String fName, String lName, String email, Date createdAt) {
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.createdAt = createdAt;
	}

	@PrePersist
	public void prePersist() {
		createdAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public List<Bill> getBills() {
		return bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}

	public void addBill(Bill bill) {
		bills.add(bill);
	}

}
