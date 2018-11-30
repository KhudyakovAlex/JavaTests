package Global;

public class Cash {
	private int cash;
	
	public Cash() {
		cash = 0;
	}
	
	public Cash(int cash) {
		this.cash = cash;
	}
	
	public int getCash() {
		return cash;
	}
	
	public void incCash(int incVal) {
		cash += incVal;
	}
	
	public void decCash(int decVal) {
		cash -= decVal;
	}
}