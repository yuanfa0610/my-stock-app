package com.rf.privjoy.myStock.impl.persistent;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="record")
public class Record {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, 
			 CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="stock_id")
	private Stock stock;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, 
			 CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(name="stock_price")
	private BigDecimal stockPrice;
	
	@Column(name="status")
	private String status;
	
	@Column(name="time")
	private Timestamp time;
	
	public Record() {
		
	}
	
	public Record(Stock stock, User user, BigDecimal stockPrice, String status, Timestamp time) {
		super();
		this.stock = stock;
		this.user = user;
		this.stockPrice = stockPrice;
		this.status = status;
		this.time = time;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Stock getStock() {
		return stock;
	}
	
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public BigDecimal getStockPrice() {
		return stockPrice;
	}
	
	public void setStockPrice(BigDecimal stockPrice) {
		this.stockPrice = stockPrice;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Timestamp getTime() {
		return time;
	}
	
	public void setTime(Timestamp time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Record [id=" + id + ", stock=" + stock + ", user=" + user + ", stockPrice=" + stockPrice
				+ ", status=" + status + ", time=" + time + "]";
	}
	
}
