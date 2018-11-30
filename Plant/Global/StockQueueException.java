package Global;

public class StockQueueException extends Exception {
	String message;
	
	StockQueueException(String message) {
		this.message = message;
	}
	
	public String toString() {
		return message;
	}
}