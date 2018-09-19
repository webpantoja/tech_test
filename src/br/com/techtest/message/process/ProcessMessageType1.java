package br.com.techtest.message.process;

import java.util.List;

import br.com.techtest.entity.Sale;
import br.com.techtest.message.IMessage;

/**
 * 
 * @author Rafael.Pantoja
 *
 */
public class ProcessMessageType1 implements IMessageProcessable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void process(IMessage message, List<Sale> saleList) {
		saleList.add(message.getSale());
	}

}
