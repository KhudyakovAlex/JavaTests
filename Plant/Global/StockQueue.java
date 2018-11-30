package Global;

public class StockQueue {
	int volume;
	final int MAX_VOLUME = 20;
	
	public StockQueue() {
        volume = 0;
	}
	
	public void put(int volume) throws StockQueueException {
		if (this.volume	+ volume > MAX_VOLUME) {
			throw new StockQueueException("Stock Queue Overloading");
		}
		this.volume	+= volume;
	}
	
	public int get(int volume) {
		int ret;
		
		if (volume > this.volume) {
			ret = this.volume;
			this.volume = 0;
		} else {
			ret = volume;
			this.volume -= volume;
		}

		return ret;
	}
	
	public int getState() {
		return volume;
	}
}