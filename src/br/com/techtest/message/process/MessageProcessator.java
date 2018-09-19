package br.com.techtest.message.process;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.techtest.entity.Sale;
import br.com.techtest.message.IMessage;
import br.com.techtest.message.MessageType1;
import br.com.techtest.message.MessageType2;
import br.com.techtest.message.MessageType3;

/**
 * 
 * @author Rafael.Pantoja
 *
 */
public final class MessageProcessator {
	
	private static Map<Class<?>, IMessageProcessable> mapProcess = new HashMap<Class<?>, IMessageProcessable>();
	
	static{
		mapProcess.put(MessageType1.class, new ProcessMessageType1());
		mapProcess.put(MessageType2.class, new ProcessMessageType2());
		mapProcess.put(MessageType3.class, new ProcessMessageType3());
	}
	
	private MessageProcessator(){
	}
	
	public static void processMessage(IMessage message, List<Sale> saleList){
		mapProcess.get(message.getClass()).process(message, saleList);
	}

}
