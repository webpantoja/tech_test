package br.com.techtest.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.techtest.entity.Sale;
import br.com.techtest.message.IMessage;
import br.com.techtest.message.process.MessageProcessator;

/**
 * 
 * @author Rafael.Pantoja
 *
 */
public class MessageReceiver {
	
	private static final Integer MAX_MESSAGE = 50;
	private Integer messageCount = 0;
	private Map<String, List<Sale>> mapSale = new HashMap<String, List<Sale>>();

	/**
	 * Process external messages
	 * 
	 * @param message
	 */
	public void receiver(IMessage message){
		if(messageCount < MAX_MESSAGE){
			messageCount++;
			if(!mapSale.containsKey(message.getSale().getProductType())){
				mapSale.put(message.getSale().getProductType(), new ArrayList<Sale>());
			}
			MessageProcessator.processMessage(message, mapSale.get(message.getSale().getProductType()));
			if(!messageCount.equals(MAX_MESSAGE) && messageCount % 10 == 0){
				printReport();
			}else if(messageCount == MAX_MESSAGE){
				System.out.println("The system reache number max message, it is stopping receive new message...");
				printReport();
			}
		}
	}
	
	private void printReport(){
		System.out.println("");
		System.out.println("##### Starting Report ######");
		System.out.println("Messages: " + messageCount);
		
		for(Map.Entry<String, List<Sale>> mapEntry: mapSale.entrySet()){
			System.out.println("");
			System.out.println("Product...: " + mapEntry.getKey());
			System.out.println("Quantity..: " + mapEntry.getValue().size());
			System.out.println("Total Sum.: $ " + mapEntry.getValue().stream().mapToDouble(o->o.getPrice()).sum());
		}
		System.out.println("##### End Report ######");
		System.out.println("");
	}
	
	/**
	 * 
	 * @return
	 */
	public Map<String, List<Sale>> getMapSale(){
		return mapSale;
	}
	
	public Integer getMessageCount(){
		return messageCount;
	}
}
