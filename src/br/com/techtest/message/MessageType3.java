package br.com.techtest.message;

import br.com.techtest.operation.OperationEnum;

/**
 * 
 * @author Rafael.Pantoja
 *
 */
public class MessageType3 extends AbstractMessage {

	private OperationEnum operation;

	/**
	 * @return the operation
	 */
	public OperationEnum getOperation() {
		return operation;
	}

	/**
	 * @param operation the operation to set
	 */
	public void setOperation(OperationEnum operation) {
		this.operation = operation;
	}
}
