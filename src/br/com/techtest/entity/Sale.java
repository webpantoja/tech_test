package br.com.techtest.entity;

/**
 * 
 * @author Rafael.Pantoja
 *
 */
public class Sale {

	private String productType;
	private Double price;
	private Integer quantity;
	
	/**
	 * 
	 */
	public Sale(){
	}
	
	/**
	 * 
	 */
	public Sale(Sale sale){
		this.setPrice(sale.getPrice());
		this.setProductType(sale.getProductType());
		this.setQuantity(sale.getQuantity());
	}

	/**
	 * @return the productType
	 */
	public String getProductType() {
		return productType;
	}
	/**
	 * @param productType the productType to set
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}
	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
