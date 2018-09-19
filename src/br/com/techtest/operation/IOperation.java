package br.com.techtest.operation;

import java.util.List;

import br.com.techtest.entity.Sale;

/**
 * 
 * @author Rafael.Pantoja
 *
 */
public interface IOperation {
	
	void process(List<Sale> saleList, Double value);

}
