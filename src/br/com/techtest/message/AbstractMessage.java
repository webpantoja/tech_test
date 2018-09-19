package br.com.techtest.message;

import br.com.techtest.entity.Sale;

/**
 * 
 * @author Rafael.Pantoja
 *
 */
public abstract class AbstractMessage implements IMessage {

	private Sale sale;

	/**
	 * @return the sale
	 */
	public Sale getSale() {
		return sale;
	}

	/**
	 * @param sale the sale to set
	 */
	public void setSale(Sale sale) {
		this.sale = sale;
	}
	
	

}
