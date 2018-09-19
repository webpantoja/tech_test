package br.com.techtest.message.process;

import java.util.List;

import br.com.techtest.entity.Sale;
import br.com.techtest.message.IMessage;
import br.com.techtest.message.MessageType3;

/**
 * 
 * @author Rafael.Pantoja
 *
 */
public class ProcessMessageType3 implements IMessageProcessable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void process(IMessage message, List<Sale> saleList) {
		((MessageType3)message).getOperation().getOperation().process(
				saleList, message.getSale().getPrice());
	}

}
