package br.com.techtest.message;

import br.com.techtest.entity.Sale;

/**
 * 
 * @author Rafael.Pantoja
 *
 */
public interface IMessage {

	void setSale(Sale sale);
	Sale getSale();
}
