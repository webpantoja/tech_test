package br.com.techtest.message.process;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import br.com.techtest.entity.Sale;
import br.com.techtest.message.IMessage;
import br.com.techtest.message.MessageType2;

/**
 * 
 * @author Rafael.Pantoja
 *
 */
public class ProcessMessageType2 implements IMessageProcessable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void process(IMessage message, List<Sale> saleList) {
		Integer quantity = ((MessageType2) message).getSale().getQuantity();
		saleList.addAll(IntStream.range(0, quantity).mapToObj(x->new Sale(message.getSale())).collect(Collectors.toList()));
	}

}
