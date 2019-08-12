package com.spring.boot.data.jpa.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tbl_bills")
public class Bill implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bill_id")
	private Long id;

	@Column(nullable = false)
	@NotEmpty
	private String description;

	@Column(nullable = false)
	@NotEmpty
	private String observation;

	@Column(name = "created_at", nullable = false)
	@NotNull
	private Date createdAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "itembill_id")
	private Client client;

	@OneToMany(mappedBy = "bill", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ItemBill> itemBills;

	public Bill() {
		itemBills = new ArrayList<>();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<ItemBill> getItemBills() {
		return itemBills;
	}

	public void setItemBills(List<ItemBill> itemBills) {
		this.itemBills = itemBills;
	}

	public void addItemBill(ItemBill itemBill) {
		itemBills.add(itemBill);
	}
	
	public float getTotalEfe() {
		float total = 0.0f;
		
		for(int i = 0; i < itemBills.size(); i++) {
			total += itemBills.get(i).calculateTax();
		}
		
		return total;
	}
}
