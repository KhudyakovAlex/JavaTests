package Global;

public class Stock {
    private int volume = 0;
	private final int MAX_VOLUME = 100;
	public String division;
	public int x, y;
	
	public Stock() {
	}
	
	public Stock(int volume, String division) {
		this.volume = volume;
		this.division = division;
	}
	
	public int getVolume() {
		return volume;
	}
	
	public void setVolume(int volume) {
		this.volume = volume;
	}
	
	public void addVolume(int volume) {
		this.volume += volume;
		if (this.volume > MAX_VOLUME) this.volume = MAX_VOLUME;
	}
	
	public int getFreeVolume() {
		return MAX_VOLUME - volume;
	}
}