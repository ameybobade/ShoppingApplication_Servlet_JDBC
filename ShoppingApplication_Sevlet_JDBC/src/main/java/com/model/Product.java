package com.model;

public class Product {
	
	private int prodId;
	private String prodName;
	private int prodQuant;
	private int prodPrice;
	public Product(int prodId, String prodName, int prodQuant, int prodPrice) {
		super();
		this.prodId = prodId;
		this.prodName = prodName;
		this.prodQuant = prodQuant;
		this.prodPrice = prodPrice;
	}
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public int getProdQuant() {
		return prodQuant;
	}
	public void setProdQuant(int prodQuant) {
		this.prodQuant = prodQuant;
	}
	public int getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(int prodPrice) {
		this.prodPrice = prodPrice;
	}
	
	
	
}
