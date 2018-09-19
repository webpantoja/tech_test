package br.com.techtest.operation;

import java.util.List;

import br.com.techtest.entity.Sale;

/**
 * 
 * @author Rafael.Pantoja
 *
 */
public class SubtractionOperation implements IOperation{

	@Override
	public void process(List<Sale> saleList, Double value) {
		saleList.forEach(o->o.setPrice(o.getPrice() - value));
	}
}
