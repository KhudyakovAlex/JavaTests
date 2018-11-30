package Global;

import Global.*;

public class StockShop {

	protected Stock[] sts = new Stock[100];
	protected int stCount;
	protected PlantMap m;
	
	public void addStock(int volume, String division) {
		sts[stCount] = new Stock(volume, division);
		m.allocate(sts[stCount]);
		stCount++;
	}
	
	public StockShop() {
		stCount = 0;
	}
	
	public int getStockCount() {
		return stCount;
	}
	
	public int getFreeVolume() {
		int freeVolume = 0;
		for (int i = 0; i < stCount; i++) freeVolume += sts[i].getFreeVolume();
		return freeVolume;
	}
	
	public int getBusyVolume() {
		int busyVolume = 0;
		for (int i = 0; i < stCount; i++) busyVolume += sts[i].getVolume();
		return busyVolume;
	}
	
	protected int getCost() {
		int c = 0;
		for (int i = 0; i < stCount; i++)
			c += Price.STOCK;
		return c;
	}
}