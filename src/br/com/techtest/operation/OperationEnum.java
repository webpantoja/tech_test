package br.com.techtest.operation;

/**
 * 
 * @author Rafael.Pantoja
 *
 */
public enum OperationEnum {
	
	ADD(new AddOperation()),
	SUBTRACT(new SubtractionOperation()),
	MULTIPLY(new MultiplyOperation());

	private IOperation operation; 
	
	private OperationEnum(IOperation operation) {
		this.operation = operation;
	}

	/**
	 * @return the operation
	 */
	public IOperation getOperation() {
		return operation;
	}

}
