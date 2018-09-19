package br.com.techtest.message.process;

import java.io.Serializable;
import java.util.List;

import br.com.techtest.entity.Sale;
import br.com.techtest.message.IMessage;

/**
 * 
 * @author Rafael.Pantoja
 *
 */
public interface IMessageProcessable extends Serializable {

	void process(IMessage message, List<Sale> saleList);
}
