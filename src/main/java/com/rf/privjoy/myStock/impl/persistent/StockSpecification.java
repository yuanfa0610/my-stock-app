package com.rf.privjoy.myStock.impl.persistent;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="stock_specification")
public class StockSpecification {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@OneToOne(mappedBy="stockSpecification",
			cascade={CascadeType.DETACH, CascadeType.MERGE, 
			 CascadeType.PERSIST, CascadeType.REFRESH})
	private Stock stock;
	
	@Column(name="price")
	private BigDecimal price;
	
	@Column(name="price_earning_ratio")
	private BigDecimal priceEarningRatio;
	
	@Column(name="price_book_ratio")
	private BigDecimal priceBookRatio;
	
	public StockSpecification() {
		
	}
	
	public StockSpecification(BigDecimal price, BigDecimal priceEarningRatio, BigDecimal priceBookRatio) {
		super();
		this.price = price;
		this.priceEarningRatio = priceEarningRatio;
		this.priceBookRatio = priceBookRatio;
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
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public BigDecimal getPriceEarningRatio() {
		return priceEarningRatio;
	}
	
	public void setPriceEarningRatio(BigDecimal priceEarningRatio) {
		this.priceEarningRatio = priceEarningRatio;
	}
	
	public BigDecimal getPriceBookRatio() {
		return priceBookRatio;
	}
	
	public void setPriceBookRatio(BigDecimal priceBookRatio) {
		this.priceBookRatio = priceBookRatio;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StockSpecification [id=" + id + ", stock=" + stock + ", price=" + price + ", priceEarningRatio="
				+ priceEarningRatio + ", priceBookRatio=" + priceBookRatio + "]";
	}
	
}
