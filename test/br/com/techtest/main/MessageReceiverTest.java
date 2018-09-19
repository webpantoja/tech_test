package br.com.techtest.main;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.techtest.entity.Sale;
import br.com.techtest.message.IMessage;
import br.com.techtest.message.MessageType1;
import br.com.techtest.message.MessageType2;
import br.com.techtest.message.MessageType3;
import br.com.techtest.operation.OperationEnum;

/**
 * @author Rafael.Pantoja
 *
 */
public class MessageReceiverTest {

	MessageReceiver messageReceiver;
	
    @Before
    public void setup() {
        this.messageReceiver = new MessageReceiver();
    }
    
	/**
	 * Test method for {@link br.com.techtest.main.MessageReceiver#receiver()}.
	 */
	@Test
	public void testReceiverMessageType1() {
		insertMessageRepeat(MessageType1.class, 3, "apple", 1, 10.0);
		insertMessageRepeat(MessageType1.class, 3, "book", 1, 15.0);
		Map<String, List<Sale>> mapResult = messageReceiver.getMapSale();
		Assert.assertNotNull(mapResult);
		Assert.assertEquals(mapResult.size(), 2);
		Assert.assertEquals(getSum(mapResult.get("apple")), (Double) 30.0);
		Assert.assertEquals(getSum(mapResult.get("book")), (Double) 45.0);
		Assert.assertTrue(messageReceiver.getMessageCount().equals(6));
	}
	
	/**
	 * Test method for {@link br.com.techtest.main.MessageReceiver#receiver()}.
	 */
	@Test
	public void testReceiverMessageType2() {
		insertMessageRepeat(MessageType2.class, 3, "apple", 5, 10.0);
		insertMessageRepeat(MessageType2.class, 3, "book", 10, 15.0);
		Map<String, List<Sale>> mapResult = messageReceiver.getMapSale();
		Assert.assertEquals(mapResult.size(), 2);
		Assert.assertEquals(getSum(mapResult.get("apple")), (Double) 150.0);
		Assert.assertEquals(getSum(mapResult.get("book")), (Double) 450.0);
		Assert.assertTrue(messageReceiver.getMessageCount().equals(6));
	}
	
	/**
	 * Test method for {@link br.com.techtest.main.MessageReceiver#receiver()}.
	 */
	@Test
	public void testChangeValueOnReceiverMessageType3() {
		insertMessageRepeat(MessageType2.class, 1, "apple", 5, 10.0);
		insertMessageRepeat(MessageType2.class, 1, "book", 10, 15.0);
		Map<String, List<Sale>> mapResult = messageReceiver.getMapSale();
		Assert.assertEquals(mapResult.size(), 2);
		Assert.assertEquals(getSum(mapResult.get("apple")), (Double) 50.0);
		Assert.assertEquals(getSum(mapResult.get("book")), (Double)	150.0);
		Assert.assertTrue(messageReceiver.getMessageCount().equals(2));
		
		Sale sale = new Sale();
		sale.setProductType("apple");
		sale.setPrice(10.0);
		MessageType3 messageType3 = new MessageType3();
		messageType3.setOperation(OperationEnum.ADD);
		messageType3.setSale(sale);
		
		messageReceiver.receiver(messageType3);
		
		mapResult = messageReceiver.getMapSale();
		Assert.assertEquals(mapResult.size(), 2);
		Assert.assertEquals(getSum(mapResult.get("apple")), (Double) 100.0);
		Assert.assertEquals(getSum(mapResult.get("book")), (Double)	150.0);
		Assert.assertTrue(messageReceiver.getMessageCount().equals(3));
		
		
		sale = new Sale();
		sale.setProductType("apple");
		sale.setPrice(5.0);
		messageType3 = new MessageType3();
		messageType3.setOperation(OperationEnum.SUBTRACT);
		messageType3.setSale(sale);
		
		messageReceiver.receiver(messageType3);
		
		mapResult = messageReceiver.getMapSale();
		Assert.assertEquals(mapResult.size(), 2);
		Assert.assertEquals(getSum(mapResult.get("apple")), (Double) 75.0);
		Assert.assertEquals(getSum(mapResult.get("book")), (Double)	150.0);
		Assert.assertTrue(messageReceiver.getMessageCount().equals(4));
		
		sale = new Sale();
		sale.setProductType("apple");
		sale.setPrice(3.0);
		messageType3 = new MessageType3();
		messageType3.setOperation(OperationEnum.MULTIPLY);
		messageType3.setSale(sale);
		
		messageReceiver.receiver(messageType3);
		
		mapResult = messageReceiver.getMapSale();
		Assert.assertEquals(mapResult.size(), 2);
		Assert.assertEquals(getSum(mapResult.get("apple")), (Double) 225.0);
		Assert.assertEquals(getSum(mapResult.get("book")), (Double)	150.0);
		Assert.assertTrue(messageReceiver.getMessageCount().equals(5));
	}
	
	/**
	 * Test method for {@link br.com.techtest.main.MessageReceiver#receiver()}.
	 */
	@Test
	public void testNoReceiverMoreThan50Messages() {
		insertMessageRepeat(MessageType1.class, 20, "apple", 1, 10.0);
		insertMessageRepeat(MessageType2.class, 20, "book", 30, 15.0);
		insertMessageRepeat(MessageType2.class, 20, "pc", 5, 500.0); 
		Map<String, List<Sale>> mapResult = messageReceiver.getMapSale();
		Assert.assertEquals(mapResult.size(), 3);
		Assert.assertEquals(getSum(mapResult.get("apple")), (Double) 200.0);
		Assert.assertEquals(getSum(mapResult.get("book")), (Double) 9000.0);
		Assert.assertEquals(getSum(mapResult.get("pc")), (Double) 25000.0);
		Assert.assertTrue(messageReceiver.getMessageCount().equals(50));
	}
	
	private IMessage createMessage(Class<?> messageType, String product, Integer quantity, Double price){
    	IMessage instance = null;
		try {
			Sale sale = new Sale();
			sale.setQuantity(quantity);
			sale.setProductType(product);
			sale.setPrice(price);
			instance = (IMessage) messageType.newInstance();
			instance.setSale(sale);
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		}
    	return instance;
    }
    
    private Double getSum(List<Sale> listaSale){
    	Double sum = new Double(0);
    	for (Sale sale : listaSale) {
    		sum+= sale.getPrice();
		}
    	return sum;
    }
    
    private void insertMessageRepeat(Class<?> messageType, Integer quantRepeat, String product, 
    		Integer quantity, Double price){
    	for(int i = 0; i < quantRepeat; i++){
    		messageReceiver.receiver(createMessage(messageType, product, quantity, price));
    	}
    }

}
